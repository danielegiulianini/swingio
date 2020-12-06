package swingio

import java.awt.Component

import cats.effect.IO

package object scala extends listeners.ListenersAliases with listeners.ListenersImplicits
  with listeners.ComponentsWithListenersImplicits with concurrency.ConcurrencyImplicits
  with concurrency.ConcurrencyUtilities {

  /** Converts from a value of generic subtype of java.awt.Component to an IO containing that value, enabling use and
   * composition inside a for-comprehension construct with the [[IO]] semantics, that is: sequential computation
   * blocks described by an IO and composed by flatmap and map results in sequential side-effect execution when run
   * by [[cats.effect.IO#unsafeRunSync]].
   * This conversion allows to automatically convert any member of [[java.awt.Component]] to IO, actually
   * getting it ready to be used in for-comprehension, and to reuse the constructors provided by the original
   * library without need to manually rewrite each of them in order to put it results into IO for enabling
   * composition in the monadic chain.
   *
   * @see [[swingio.scala.examples.ExampleOfUse]] for example of how to compose IOs, and
   * [[IO]] for a detailed description of the IO data type.*/
  implicit def fromGenericTypeToIO[T<:Component](fa : T) : IO[T] = IO{ fa }
}
