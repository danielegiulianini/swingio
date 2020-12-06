package swingio.scala.components

import java.awt.{Component, Dimension, Font}
import java.awt.event.{ComponentListener, MouseListener}

import cats.effect.IO

trait ComponentImplicits {
  implicit class ComponentIO(component: Component) {
    /** Returns an [[IO]] containing the description of a [[java.awt.Component#addMouseListener]]
     * method invocation.*/
    def addMouseListener(l:MouseListener): IO[Unit] = IO {component.addMouseListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#removeMouseListener]]
     * method invocation.*/
    def removeMouseListener(l:MouseListener): Unit = IO { component.removeMouseListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#addComponentListener]]
     * method invocation. */
    def addComponentListener(l: ComponentListener): IO[Unit] = IO { component.addComponentListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#removeComponentListener]]
     * method invocation. */
    def removeComponentListener(l: ComponentListener): IO[Unit] = IO { component.removeComponentListener(l) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#getFont]]
     * method invocation. */
    def getFont(): IO[Font] = IO {component.getFont}

    /** Returns an [[IO]] containing the description of a [[java.awt.Component#setPreferredSize]]
     * method invocation. */
    def setPreferredSize(d: Dimension): IO[Unit] = IO {component.setPreferredSize(d) }
  }
}
