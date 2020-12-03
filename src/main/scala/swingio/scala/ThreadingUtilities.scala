package swingio.scala

import cats.effect.IO

object ThreadingUtilities {
  implicit def fromByNameUnitToRunnable(fa : => Unit): Runnable = () => fa

}
