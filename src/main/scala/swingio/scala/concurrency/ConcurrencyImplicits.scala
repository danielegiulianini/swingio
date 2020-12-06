package swingio.scala.concurrency

trait ConcurrencyImplicits {
  implicit def fromByNameUnitToRunnable(fa : => Unit): Runnable = () => fa
}
