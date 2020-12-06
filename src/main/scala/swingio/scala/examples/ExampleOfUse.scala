package swingio.scala.examples

import java.awt.BorderLayout

import cats.effect.IO
import javax.swing._

object ExampleOfUse extends App {

  import swingio.scala._

  val frameBuilt = for {
    frame <- new JFrame
    _ <- frame.setTitle("Basic GUI with listeners")
    _ <- frame.setSize(400, 400)
    _ <- frame.setResizable(true)
    _ <- frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  } yield frame

  val panelBuilt = for {
    panel <- new JPanel()
    slider <- new JSlider()
    label <- new JLabel("value: ?")
    button <- new JButton("Click to display positive value.")
    _ <- monadicInvokeAndWait(for {
      _ <- panel.setLayout(new BorderLayout())
      _ <- panel.add(slider, BorderLayout.CENTER)
      _ <- panel.add(label, BorderLayout.NORTH)
      //monadic listener:
      _ <- button.addMonadicActionListener(for {
        currentValue <- slider.getValue
        _ <- if (currentValue > 0) label.setText("value: " + currentValue)
        else IO.unit
      } yield ())
      //procedural listener:
      _ <- button.addActionListener(System.out.println("button pressed"))
      _ <- panel.add(slider, BorderLayout.CENTER)
      _ <- panel.add(label, BorderLayout.NORTH)
      _ <- panel.add(button, BorderLayout.SOUTH)
    } yield ())
  } yield panel

  val program = for {
    frame <- frameBuilt
    panel <- panelBuilt
    _ <- frame.getContentPane.add(panel)
    _ <- frame.setVisible(true)
  } yield ()

  program unsafeRunSync

  //example of execution with unsafeRunAsync (async, callback-based API)
  program unsafeRunAsync {
    case Left(_) => println("an exception was raised during gui-building.")
    case _ => println("gui-building ok.")
  }
}
