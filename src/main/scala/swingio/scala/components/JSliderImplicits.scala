package swingio.scala.components

import cats.effect.IO
import javax.swing.event.ChangeListener
import javax.swing.JSlider
import swingio.scala.MonadicChangeListener

object JSliderImplicits {

  /** A class that provides a monadic description of the operations supplied by Swing's [[JSlider]] in the form
   * of IO monad in a purely functional fashion. */
  implicit class JSliderIO(jSlider: JSlider){

    /** Returns an [[IO]] containing the description of a [[JSlider#setMinimum]]
     * method invocation on this instance. */
    def minimumSet(min: Int): IO[Unit] = IO { jSlider.setMinimum(min) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setMaximum]]
     * method invocation on this instance. */
    def maximumSet(max: Int): IO[Unit] = IO { jSlider.setMaximum(max) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setValue]]
     * method invocation on this instance. */
    def valueSet(value: Int): IO[Unit] = IO { jSlider.setValue(value) }

    /** Returns an [[IO]] containing the description of a [[JSlider#getValue]]
     * method invocation on this instance. */
    def valueGot: IO[Int] = IO { jSlider.getValue }

    /** Returns an [[IO]] containing the description of a [[JSlider#setMajorTickSpacing]]
     * method invocation on this instance. */
    def majorTickSpacingSet(spacing: Int): IO[Unit] = IO { jSlider.setMajorTickSpacing(spacing) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setMinorTickSpacing]]
     * method invocation on this instance. */
    def minorTickSpacingSet(spacing: Int): IO[Unit] = IO { jSlider.setMinorTickSpacing(spacing) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setPaintTicks]]
     * method invocation on this instance. */
    def paintTicksSet(b: Boolean): IO[Unit] = IO { jSlider.setPaintTicks(b) }

    /** Returns an [[IO]] containing the description of a [[JSlider#setPaintLabels]]
     * method invocation on this instance. */
    def paintLabelsSet(b: Boolean): IO[Unit] = IO { jSlider.setPaintLabels(b) }

    //procedural event listener description:
    /** Returns an [[IO]] that registers an [[ChangeListener]] for handling the
     * [[javax.swing.event.ChangeEvent]]s generated by this instance.
     * It accepts as parameter a procedural specification of the listener instead of a
     * monadic one.
     * @see [[swingio.scala.listeners.ListenersAliases.MonadicChangeListener]] and
     *      [[swingio.scala.components.JSliderImplicits#addMonadicChangeListener]]
     *      for a monadic listener description.*/
    def changeListenerAdded(l: ChangeListener): IO[Unit] = IO {jSlider.addChangeListener(l)}

    /** Returns an [[IO]] containing the code for unregistering the given [[ChangeListener]] from
     * this instance. */
    def changeListenerRemoved(l: ChangeListener): IO[Unit] = IO {jSlider.removeChangeListener(l)}

    /** Returns an [[IO]] that registers a [[swingio.scala.listeners.ListenersAliases.MonadicChangeListener]]
     * for handling the [[javax.swing.event.ChangeEvent]]s generated by this instance.
     * It accepts as parameter a monadic specification of the listener instead of a procedural one.
     *
     * @see [[changeListenerAdded]] for a procedural listener description.*/
    def monadicChangeListenerAdded(l: MonadicChangeListener): Unit =
      IO {jSlider.addChangeListener(l(_).unsafeRunSync())}

    /** Returns an [[IO]] containing the code for unregistering the given
     * [[swingio.scala.listeners.ListenersAliases.MonadicChangeListener]] from
     * this instance. */
    def monadicChangeListenerRemoved(l: MonadicChangeListener): Unit =
      IO {jSlider.removeChangeListener(l(_).unsafeRunSync())}
  }

}
