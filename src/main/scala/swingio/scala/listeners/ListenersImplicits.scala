package swingio.scala.listeners

import cats.effect.IO
import swingio.scala._

trait ListenersImplicits {
  implicit def unitToActionListener[T](f: => Unit): T => Unit = _ => f
  implicit def unitToMonadicActionListener(f: => IO[Unit]): MonadicActionListener = _ => f.unsafeRunSync()
}
