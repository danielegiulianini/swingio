package swingio.scala.examples

import java.awt.BorderLayout

import cats.effect.IO
import javax.swing._

import swingio.scala._
import swingio.scala.components.ContainerImplicits._
import swingio.scala.components.JButtonImplicits._
import swingio.scala.components.JLabelImplicits._
import swingio.scala.components.JSliderImplicits._
import swingio.scala.components.JFrameImplicits._

object ExampleOfUse extends App {

  val frameBuilt = for {
    frame <- new JFrame()
    _ <- frame.titleSet("Basic GUI with listeners")
    _ <- frame.resizableSet(true)
    _ <- frame.defaultCloseOperationSet(WindowConstants.EXIT_ON_CLOSE)
  } yield frame

  val panelBuilt = for {
    panel <- new JPanel()
    slider <- new JSlider()
    label <- new JLabel("value: ?")
    button <- new JButton("Click to display positive value.")
    _ <- monadicInvokeAndWait(for {
      _ <- panel.layoutSet(new BorderLayout())
      _ <- panel.added(slider, BorderLayout.CENTER)
      _ <- panel.added(label, BorderLayout.NORTH)
      //monadic listener description:
      _ <- button.monadicActionListenerAdded(for {
        currentValue <- slider.valueGot
        _ <- if (currentValue > 0) label.textSet("value: ".+(currentValue))
        else IO.unit
      } yield ())
      //procedural listener:
      _ <- button.actionListenerAdded(System.out.println("button pressed"))
      _ <- panel.added(slider, BorderLayout.CENTER)
      _ <- panel.added(label, BorderLayout.NORTH)
      _ <- panel.added(button, BorderLayout.SOUTH)
    } yield ())
  } yield panel

  val program = for {
    frame <- frameBuilt
    panel <- panelBuilt
    cp <- frame.contentPane
    _ <- cp.added(panel)
    _ <- frame.visibleSet(true)
  } yield ()

  program unsafeRunSync
}
