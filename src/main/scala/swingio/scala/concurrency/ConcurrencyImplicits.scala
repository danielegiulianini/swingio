package swingio.scala.concurrency

import cats.effect.IO

import scala.concurrent.Promise

trait ConcurrencyImplicits {

  /** Implicit utility for converting a by-name (or unevaluated) parameter expression provided by => syntax to
   * [[java.lang.Runnable]] for enabling a more concise syntax at call-side when describing their behaviour.*/
  implicit def fromByNameUnitToRunnable(fa : => Unit): Runnable = () => fa


  implicit def fromPromiseToIOOfPromise[T](promise: Promise[T]) : IO[Promise[T]] = IO{promise}
}
