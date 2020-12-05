package swingio.scala.examples

import java.awt.BorderLayout

import cats.effect.IO
import javax.swing._
import swingio.scala.ThreadingUtilities.Monadic.monadicInvokeAndWait

object ExampleOfUse extends App {

  import swingio.scala.Listeners._
  import swingio.scala._

  val frameBuilt = for {
    frame <- new JFrame
    _ <- frame.setTitle("Basic GUI with listeners")
    _ <- frame.setSize(400, 400)
    _ <- frame.setResizable(true)
    _ <- frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  } yield frame

  val panelBuilt = for {
    _ <- Thread.sleep(100000)
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
      _ <- button.addActionListener(_ => System.out.println("button pressed"))
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

  //example of execution with unsafeRunAsync (async, callback-based API)
  program unsafeRunAsync {
    case Left(_) => println("an exception was raised during gui-building.")
    case _ => println("gui-building ok.")
  }

  /*val program = for {
    jf <- new JFrame()
    jp <- new JPanel
    _ <- jf.add(jp)
    _ <- jf.setSize(320, 300)
    //_ <- SwingUtilities.invokeAndWait(for{      _ <- println("ciao")    } yield ())
    b <- new JButton("cua")
    _ <- b.addMonadicActionListener(for {
      //_ <- e.getActionCommand
      _ <- print("hello")
    } yield())
    _ <- jp.add(b)
    _ <- jf.setVisible(true)
  } yield ()*/

}
