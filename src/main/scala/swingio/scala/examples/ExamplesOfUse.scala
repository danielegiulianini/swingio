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

/** Contains a simple example of use for building a Basic GUI with the classes contained in this library. */
object SimpleExampleWithSwingIo extends App {
  val frameBuilt = for {
    frame <- new JFrame()
    _ <- frame.titleSet("Basic GUI")
    _ <- frame.sizeSet(320, 200)
  } yield frame

  val panelBuilt = for {
    panel <- new JPanel()
    cb <- new JButton("Close program.")
    _ <- cb.actionListenerAdded(System.exit(0))
    _ <- panel.added(cb)
  } yield panel

  val program = for {
    frame <- frameBuilt
    panel <- panelBuilt
    _ <- frame.added(panel)
    _ <- frame.visibleSet(true)
  } yield ()

  program unsafeRunSync
}


/** Contains the procedural/OO-equivalent code of [[SimpleExampleWithSwingIo]] leveraging traditional
 * swing APIs. */
object SimpleExampleWithTraditionalSwing extends App {
  def buildFrame = {
    val frame = new JFrame
    frame.setTitle("Basic GUI")
    frame.setSize(320, 200)
    frame
  }

  def buildPanel = {
    val panel = new JPanel
    val b = new JButton("Close program")
    b.addActionListener(_ => System.exit(0))
    panel.add(b)
    panel
  }

  val panel = buildPanel
  val frame = buildFrame
  frame.add(panel)
  frame.setVisible(true)
}

/** Contains a simple example of a GUI with a comparison of procedural vs monadic
 * action listener specification. */
object ExampleWithMonadicVsProceduralListeners extends App {

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


/** Contains a simple example of use of the classes of this library for building a frame with explicit use
 * of layout managers in a thread-safe manner. */
object ThreadSafeExampleWithLayoutWithSwingIo extends App {
  val threadSafelyBuiltFrame = for {
    frame <- new JFrame("Basic GUI with layouts")
    _ <- monadicInvokeAndWait(for {
      _ <- frame.sizeSet(320, 200)
      _ <- frame.defaultCloseOperationSet(WindowConstants.EXIT_ON_CLOSE)
    } yield ())
  } yield frame

  val threadSafelyBuiltPanel = for {
    panel <- new JPanel()
    _ <- monadicInvokeAndWait(
      for {
        _ <- panel.layoutSet(new BorderLayout ())
        nb <- new JButton("North")
        _ <- panel.added(nb, BorderLayout.NORTH)
        wb <- new JButton("West")
        _ <- panel.added(wb, BorderLayout.WEST)
        eb <- new JButton("East")
        _ <- panel.added(eb, BorderLayout.EAST)
        sb <- new JButton("South")
        _ <- panel.added(sb, BorderLayout.SOUTH)
        cb <- new JButton("Center (close program)")
        _ <- cb.actionListenerAdded(System.exit (0))
        _ <- panel.added(cb, BorderLayout.CENTER)
      } yield ())
  } yield panel

  val threadSafeProgram = for {
    frame <- threadSafelyBuiltFrame
    panel <- threadSafelyBuiltPanel
    _ <- monadicInvokeAndWait(frame.added(panel))
    _ <- monadicInvokeAndWait(frame.visibleSet(true))
  } yield ()

  threadSafeProgram unsafeRunSync
}
