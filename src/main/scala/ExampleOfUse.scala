import java.awt.BorderLayout

import ExampleOfSimpleMVC.Model.{Model, modelUpdated}
import cats.effect.Async.fromFuture
import cats.effect.{ContextShift, IO}
import javax.swing.{JButton, JFrame, JLabel, JPanel, JSlider, WindowConstants}


object ExampleOfUse extends App {

  import swingio.scala._
  import swingio.scala.Listeners._

  val frameBuilt = for {
    frame <- new JFrame
    _ <- frame.setTitle("Basic GUI with listeners")
    _ <- frame.setSize(400, 400)
    _ <- frame.setResizable(true)
    _ <- frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  } yield frame

  val panelBuilt = for {
    panel <- new JPanel()
    _ <- panel.setLayout(new BorderLayout())
    slider <- new JSlider()
    _ <- panel.add(slider, BorderLayout.CENTER)
    label <- new JLabel("value: ?")
    _ <- panel.add(label, BorderLayout.NORTH)
    button <- new JButton("Click to display positive value.")
    //monadic listener:
    _ <- button.addMonadicActionListener(for {
      currentValue <- slider.getValue
      _ <- if (currentValue > 0) label.setText("value: " + currentValue)
      else IO.unit
    } yield ())
    //procedural listener:
    _ <- button.addActionListener((_)=>System.out.println("button pressed"))
    _ <- panel.add(button, BorderLayout.SOUTH)
  } yield panel

  val program = for {
    frame <- frameBuilt
    panel <- panelBuilt
    _ <- frame.add(panel)
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

  program unsafeRunSync
}



