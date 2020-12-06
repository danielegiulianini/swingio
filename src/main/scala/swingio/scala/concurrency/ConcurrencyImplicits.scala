package swingio.scala.concurrency

import cats.effect.IO

import scala.concurrent.Promise

trait ConcurrencyImplicits {
  implicit def fromByNameUnitToRunnable(fa : => Unit): Runnable = () => fa
  implicit def fromPromiseToIOOfPromise[T](promise: Promise[T]) : IO[Promise[T]] = IO{promise}
}
