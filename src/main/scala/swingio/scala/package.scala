package swingio

import cats.effect.IO

package object scala extends listeners.ListenersAliases with listeners.ListenersImplicits
  with listeners.ComponentsWithListenersImplicits with concurrency.ConcurrencyImplicits
  with concurrency.ConcurrencyUtilities {

  /** Converts from a value of generic type T to an IO containing that value, enabling use and
   * composition inside a for-comprehension construct with the [[IO]] semantics, that is: sequential computation
   * blocks described by an IO and composed by flatmap and map results in sequential side-effect execution when run
   * by [[cats.effect.IO#unsafeRunSync]].
   * @see [[swingio.scala.examples.ExampleOfUse]] for example of how to compose IOs, and
   * [[IO]] for a detailed description of the IO data type.*/
  //implicit def fromGenericTypeToIO[T](fa : T) : IO[T] = IO{ fa }
}
