package swingio

import cats.effect.IO
import javax.swing.JButton
import swingio.scala.Listeners.MonadicActionListener

package object scala {
  implicit def fromGenericTypeToIO[T](fa : => T) : IO[T] = IO{ fa }

  implicit class JButtonIO (button : JButton){
    def addMonadicActionListener(l : MonadicActionListener): Unit = button.addActionListener(l(_).unsafeRunSync())
  }
}
