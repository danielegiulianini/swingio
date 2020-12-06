package swingio.scala.listeners

import java.awt.event.ActionEvent

import cats.effect.IO

trait ListenersAliases {
  type MonadicActionListener = ActionEvent => IO[Unit]
}
