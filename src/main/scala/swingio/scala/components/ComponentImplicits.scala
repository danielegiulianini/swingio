package swingio.scala.components

import java.awt.{Component, Dimension, Font}
import java.awt.event.{ComponentListener, MouseListener}

import cats.effect.IO

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
