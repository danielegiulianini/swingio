package swingio.scala

import cats.effect.IO

object ThreadingUtilities {
  implicit def fromByNameUnitToRunnable(fa : => Unit): Runnable = () => fa

  implicit def fromMonadicByNameUnitToRunnable(fa : => IO[Unit]): Runnable = () => fa.unsafeRunSync()
}
