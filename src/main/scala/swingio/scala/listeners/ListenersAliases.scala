package swingio.scala.listeners

import java.awt.event.ActionEvent

import cats.effect.IO
import javax.swing.event.ChangeEvent

trait ListenersAliases {

  /** Type alias for a monadic action listener, i.e. a listener whose behaviour upon [[ActionEvent]] is
   * described by an IO monad (possibly result of a composition of IO monads).
   * It is the monadic alternative to [[java.awt.event.ActionListener]] and it is used by
   * [[swingio.scala.listeners.ComponentsWithListenersImplicits.JButtonIO#addMonadicActionListener]]*/
  type MonadicActionListener = ActionEvent => IO[Unit]

  /** Type alias for a monadic change listener, i.e. a listener whose behaviour upon [[ChangeEvent]] is
   * described by an IO monad (possibly result of a composition of IO monads).
   * It is the monadic alternative to [[javax.swing.event.ChangeListener]] and it is used by
   * [[swingio.scala.listeners.ComponentsWithListenersImplicits.JSliderIO#addMonadicChangeListener]]*/
  type MonadicChangeListener = ChangeEvent => IO[Unit]
}
