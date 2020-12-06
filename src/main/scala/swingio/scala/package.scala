package swingio

import cats.effect.IO

package object scala extends listeners.ListenersAliases with listeners.ListenersImplicits
  with listeners.ComponentsWithListenersImplicits with concurrency.ConcurrencyImplicits
  with concurrency.ConcurrencyUtilities {

  implicit def fromGenericTypeToIO[T](fa : => T) : IO[T] = IO{ fa }
}
