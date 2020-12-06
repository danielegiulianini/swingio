package swingio.scala.components

import cats.effect.IO
import javax.swing.event.ChangeListener
import javax.swing.JSlider

trait JSliderImplicits {

  /** A class that provides a monadic description of the operations supplied by Swing's [[JSlider]] in the form
   * of IO monad in a purely functional fashion.
   * @param jSlider the jSlider that this class wraps.
   */
  class JSliderIO(jSlider: JSlider){


    def setMinimum(min: Int): IO[Unit] = IO { jSlider.setMinimum(min) }

    def setMaximum(max: Int): IO[Unit] = IO { jSlider.setMaximum(max) }


    def setValue(value: Int): IO[Unit] = IO { jSlider.setValue(value) }


    def getValue: IO[Int] = IO { jSlider.getValue }


    def setMajorTickSpacing(spacing: Int): IO[Unit] = IO { jSlider.setMajorTickSpacing(spacing) }


    def setMinorTickSpacing(spacing: Int): IO[Unit] = IO { jSlider.setMinorTickSpacing(spacing) }


    def setPaintTicks(b: Boolean): IO[Unit] = IO { jSlider.setPaintTicks(b) }


    def setPaintLabels(b: Boolean): IO[Unit] = IO { jSlider.setPaintLabels(b) }


    def addChangeListener(l: ChangeListener): IO[Unit] = IO {jSlider.addChangeListener(l)}

    
    def removeChangeListener(l: ChangeListener): IO[Unit] = IO {jSlider.removeChangeListener(l)}

  }

}
