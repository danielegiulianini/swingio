package swingio.scala.listeners

import java.awt.event.ActionEvent

import cats.effect.IO
import javax.swing.event.ChangeEvent

trait ListenersAliases {
  type MonadicActionListener = ActionEvent => IO[Unit]

  /** Type alias for a monadic change listener, i.e. a listener whose behaviour upon [[ChangeEvent]] is
   * described by an IO monad (possibly result of a composition of IO monads).
   * It is the alternative to [[javax.swing.event.ChangeListener]]. */
  type MonadicChangeListener = ChangeEvent => IO[Unit]
}
