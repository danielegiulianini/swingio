package swingio.scala.components

import java.awt.{Component, Dimension, Font}
import java.awt.event.{ComponentListener, MouseListener}

import cats.effect.IO

object ComponentImplicits {

  implicit class ComponentIO(component: Component) {
    /** Returns an [[IO]] containing the description of a [[java.awt.Component#addMouseListener]]
     * method invocation.*/
    def mouseListenerAdded(l:MouseListener): IO[Unit] = IO {component.addMouseListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#removeMouseListener]]
     * method invocation.*/
    def mouseListenerRemoved(l:MouseListener): Unit = IO { component.removeMouseListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#addComponentListener]]
     * method invocation. */
    def componentListenerAdded(l: ComponentListener): IO[Unit] = IO { component.addComponentListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#removeComponentListener]]
     * method invocation. */
    def componentListenerRemoved(l: ComponentListener): IO[Unit] = IO { component.removeComponentListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#getFont]]
     * method invocation. */
    def fontGot(): IO[Font] = IO {component.getFont}

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#setPreferredSize]]
     * method invocation. */
    def preferredSizeSet(d: Dimension): IO[Unit] = IO {component.setPreferredSize(d) }
  }
}
