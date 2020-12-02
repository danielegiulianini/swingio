package swingio

import java.awt.event.ActionEvent

import cats.effect.IO

package object scala {
  implicit def fromGenericTypeToIO[T](fa : => T) : IO[T] = IO{ fa }

  type MonadicActionListener = ActionEvent => IO[Unit]

  implicit def unitToActionListener[T](f: =>Unit): T => Unit = _ => f

  implicit def unitToMonadicActionListener(f: =>IO[Unit]): MonadicActionListener = _ => f.unsafeRunSync()
  
}
