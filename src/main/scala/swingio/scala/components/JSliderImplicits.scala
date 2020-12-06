package swingio.scala.components

import cats.effect.IO
import javax.swing.event.ChangeListener
import javax.swing.JSlider

object JSliderImplicits {

  /** A class that provides a monadic description of the operations supplied by Swing's [[JSlider]] in the form
   * of IO monad in a purely functional fashion.
   */
  class JSliderIO(jSlider: JSlider){

    /** Returns an [[IO]] containing the description of a [[JSlider#setMinimum]]
     * method invocation on this instance. */
    def setMinimum(min: Int): IO[Unit] = IO { jSlider.setMinimum(min) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setMaximum]]
     * method invocation on this instance. */
    def setMaximum(max: Int): IO[Unit] = IO { jSlider.setMaximum(max) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setValue]]
     * method invocation on this instance. */
    def setValue(value: Int): IO[Unit] = IO { jSlider.setValue(value) }

    /** Returns an [[IO]] containing the description of a [[JSlider#getValue]]
     * method invocation on tthis instance. */
    def getValue: IO[Int] = IO { jSlider.getValue }

    /** Returns an [[IO]] containing the description of a [[JSlider#setMajorTickSpacing]]
     * method invocation on this instance. */
    def setMajorTickSpacing(spacing: Int): IO[Unit] = IO { jSlider.setMajorTickSpacing(spacing) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setMinorTickSpacing]]
     * method invocation on this instance. */
    def setMinorTickSpacing(spacing: Int): IO[Unit] = IO { jSlider.setMinorTickSpacing(spacing) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setPaintTicks]]
     * method invocation on this instance. */
    def setPaintTicks(b: Boolean): IO[Unit] = IO { jSlider.setPaintTicks(b) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setPaintLabels]]
     * method invocation on this instance. */
    def setPaintLabels(b: Boolean): IO[Unit] = IO { jSlider.setPaintLabels(b) }

    //procedural event listener description:
    /** Returns an [[IO]] that registers an [[ChangeListener]] for handling the
     * [[javax.swing.event.ChangeEvent]]s generated by this instance.
     * It accepts as parameter a procedural specification of the listener instead of a
     * monadic one.
     * @see [[swingio.scala.listeners.ListenersAliases.MonadicChangeListener]] and
     *      [[swingio.scala.listeners.ComponentsWithListenersImplicits.JSliderIO#addMonadicChangeListener]]
     *      for a monadic listener description.*/
    def addChangeListener(l: ChangeListener): IO[Unit] = IO {jSlider.addChangeListener(l)}

    /** Returns an [[IO]] containing the code for unregistering the given [[ChangeListener]] from
     * this instance. */
    def removeChangeListener(l: ChangeListener): IO[Unit] = IO {jSlider.removeChangeListener(l)}

  }

}
