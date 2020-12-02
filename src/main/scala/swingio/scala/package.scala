package swingio

import cats.effect.IO

package object scala {
  implicit def fromGenericTypeToIO[T](fa : => T) : IO[T] = IO{ fa }
}
