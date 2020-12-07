package swingio.scala.listeners

import java.awt.event.ActionListener

import cats.effect.IO
import javax.swing.event.ChangeEvent
import swingio.scala._

trait ListenersImplicits {
  /*def unitToTToUnitAsT2[T, T2](f: Unit)(implicit x: (T => Unit) => T2): T2 = x(_ => f)
  implicit def unitToActionListener[T](f: Unit) = unitToTToUnitAsT2[ActionEvent, ActionListener](f)*/

  /** Implicit utility for converting a by-name (or unevaluated) parameter expression provided by => syntax to
   * [[ActionListener]] for enabling a more concise syntax at call-side when describing listeners that ignores
   * the [[java.awt.event.ActionEvent]] triggering.*/
  implicit def unitToActionListener[T](f: Unit) : ActionListener = _ => f

  /** Implicit utility for converting a by-name (or unevaluated) parameter expression provided by => syntax to
   * [[MonadicActionListener]] for enabling a more concise syntax at call-side when describing listeners that ignores
   * the [[java.awt.event.ActionEvent]] triggering.*/
  implicit def unitToMonadicActionListener(f: => IO[Unit]): MonadicActionListener = _ => f

  /** Implicit utility for converting a by-name (or unevaluated) parameter expression provided by => syntax to
   * [[MonadicChangeListener]] for enabling a more concise syntax at call-side when describing
   * [[javax.swing.event.ChangeListener]]s that ignores the [[ChangeEvent]] triggering.*/
  implicit def unitToMonadicChangeListener(f: => IO[Unit]): MonadicChangeListener = _ => f

}
