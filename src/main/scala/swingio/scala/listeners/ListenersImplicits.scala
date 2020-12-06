package swingio.scala.listeners

import cats.effect.IO
import javax.swing.event.ChangeEvent
import swingio.scala._

trait ListenersImplicits {
  implicit def unitToActionListener[T](f: => Unit): T => Unit = _ => f
  implicit def unitToMonadicActionListener(f: => IO[Unit]): MonadicActionListener = _ => f.unsafeRunSync()

  /** Implicit utility for converting a by-name (or unevaluated) parameter expression provided by => syntax to
   * [[MonadicChangeListener]] for enabling a more concise syntax at call-side when describing
   * [[javax.swing.event.ChangeListener]]s that ignores
   * the [[ChangeEvent]] triggering.*/
  implicit def unitToMonadicChangeListener(f: => IO[Unit]): MonadicChangeListener = _ => f

}
