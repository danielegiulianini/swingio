package swingio.scala.listeners

import java.awt.event.{ActionEvent, ActionListener}

import cats.effect.IO
import javax.swing.SwingUtilities
import javax.swing.event.ChangeEvent
import swingio.scala._

trait ListenersImplicits {
  /*def unitToTToUnitAsT2[T, T2](f: Unit)(implicit x: (T => Unit) => T2): T2 = x(_ => f)
  implicit def unitToActionListener[T](f: Unit) = unitToTToUnitAsT2[ActionEvent, ActionListener](f)*/

  implicit def unitToActionListener[T](f: Unit) : ActionListener = _ => f

  implicit def unitToMonadicActionListener(f: => IO[Unit]): MonadicActionListener = _ => f.unsafeRunSync()

  /** Implicit utility for converting a by-name (or unevaluated) parameter expression provided by => syntax to
   * [[MonadicChangeListener]] for enabling a more concise syntax at call-side when describing
   * [[javax.swing.event.ChangeListener]]s that ignores the [[ChangeEvent]] triggering.*/
  implicit def unitToMonadicChangeListener(f: => IO[Unit]): MonadicChangeListener = _ => f

}
