package swingio.scala.components

import java.awt.{Component, Dimension, Font}
import java.awt.event.{ComponentListener, MouseListener}

import cats.effect.IO

/** Contains implicit class that enrichs [[Component]] with additional functionalities by leveraging what is
 * referred to as "Pimp My Library Pattern".
 * Use this import:
 * {{{
 * import swingio.scala._
 * import swingio.scala.components.ComponentImplicits._
 * }}}
 * to enable methods returning [[IO]] for combining methods invocation inside for-comprehension and
 * providing a description of GUI-related logic.
 *
 * @see [[IO]] for details on IO monad and how to run it. */
object ComponentImplicits {

  implicit class ComponentIO(component: Component) {

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#addMouseListener]]
     * method invocation on this instance.*/
    def mouseListenerAdded(l:MouseListener): IO[Unit] = IO {component.addMouseListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#removeMouseListener]]
     * method invocation on this instance.*/
    def mouseListenerRemoved(l:MouseListener): Unit = IO { component.removeMouseListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#addComponentListener]]
     * method invocation on this instance. */
    def componentListenerAdded(l: ComponentListener): IO[Unit] = IO { component.addComponentListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#removeComponentListener]]
     * method invocation on this instance. */
    def componentListenerRemoved(l: ComponentListener): IO[Unit] = IO { component.removeComponentListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#getFont]]
     * method invocation on this instance. */
    def fontGot(): IO[Font] = IO {component.getFont}

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#setPreferredSize]]
     * method invocation on this instance. */
    def preferredSizeSet(d: Dimension): IO[Unit] = IO {component.setPreferredSize(d) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#revalidate]]
     * method invocation on this instance. */
    def revalidated(): IO[Unit] = IO {component.revalidate()}

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#repaint]]
     * method invocation on this instance. */
    def repainted(): IO[Unit] = IO {component.repaint()}
  }
}
