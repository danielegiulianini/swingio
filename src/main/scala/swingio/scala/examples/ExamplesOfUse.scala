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
object SimpleExampleWithSwingMonadic extends App {
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


/** Contains the procedural/OO-equivalent code of [[SimpleExampleWithSwingMonadic]] leveraging traditional
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

