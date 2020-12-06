package swingio.scala.listeners

import javax.swing.JButton
import swingio.scala.MonadicActionListener

trait ComponentsWithListenersImplicits {
  implicit class JButtonIO (button : JButton){
    def addMonadicActionListener(l : MonadicActionListener): Unit = button.addActionListener(l(_).unsafeRunSync())
  }

}
