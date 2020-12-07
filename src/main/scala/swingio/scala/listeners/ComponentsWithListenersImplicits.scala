package swingio.scala.listeners

import javax.swing.{JButton, JSlider}
import swingio.scala.{MonadicActionListener, MonadicChangeListener}

trait ComponentsWithListenersImplicits {
/*
  implicit class JButtonIO (button : JButton){

    def monadicActionListenerAdded(l : MonadicActionListener): Unit = button.addActionListener(l(_).unsafeRunSync())

    def monadicActionListenerRemoved(l: MonadicActionListener): Unit = button.removeActionListener(l(_).unsafeRunSync())
  }

  implicit class JSliderIO (slider : JSlider) {

    def monadicChangeListenerAdded(l: MonadicChangeListener): Unit = slider.addChangeListener(l(_).unsafeRunSync())

    def monadicChangeListenerRemoved(l: MonadicChangeListener): Unit = slider.removeChangeListener(l(_).unsafeRunSync())
  }*/
}
