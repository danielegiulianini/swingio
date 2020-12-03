package swingio.scala

import java.awt.event.ActionEvent

import cats.effect.IO

object Listeners {
  type MonadicActionListener = ActionEvent => IO[Unit]
  implicit def unitToActionListener[T](f: =>Unit): T => Unit = _ => f
  implicit def unitToMonadicActionListener(f: =>IO[Unit]): MonadicActionListener = _ => f.unsafeRunSync()
}
