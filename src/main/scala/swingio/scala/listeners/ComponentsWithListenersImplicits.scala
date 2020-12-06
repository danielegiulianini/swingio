package swingio.scala.listeners

import javax.swing.{JButton, JSlider}
import swingio.scala.{MonadicActionListener, MonadicChangeListener}

trait ComponentsWithListenersImplicits {
  implicit class JButtonIO (button : JButton){
    def addMonadicActionListener(l : MonadicActionListener): Unit = button.addActionListener(l(_).unsafeRunSync())
  }

  implicit class JSliderIO (button : JSlider) {
    def addMonadicChangeListener(l: MonadicChangeListener): Unit = button.addChangeListener(l(_).unsafeRunSync())

    def removeMonadicChangeListener(l: MonadicChangeListener): Unit = button.removeChangeListener(l(_).unsafeRunSync())
  }
}
