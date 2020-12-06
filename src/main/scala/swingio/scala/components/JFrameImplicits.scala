package swingio.scala.components

import java.awt.{Component, Container, Frame}

import cats.effect.IO
import javax.swing.JFrame


object JFrameImplicits {

  implicit class JFrameIO(jFrame: JFrame) {

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#getContentPane]]
     * method invocation. */
    def contentPane(): IO[Container] = IO { jFrame.getContentPane() }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setResizable]]
     * method invocation. */
    def resizableSet(resizable: Boolean): IO[Unit] = IO {
      component.setResizable(resizable)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setVisible]]
     * method invocation. */
    def visibleSet(b: Boolean): IO[Unit] = IO {
      component.setVisible(b)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#pack]]
     * method invocation. */
    def packed(): IO[Unit] = IO {
      component.pack()
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setSize]]
     * method invocation. */
    def sizeSet(width: Int, height: Int): IO[Unit] = IO {
      component.setSize(width, height)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setLocationRelativeTo]]
     * method invocation. */
    def locationRelativeToSet(c: Component): IO[Unit] = IO {
      component.setLocationRelativeTo(c)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setDefaultCloseOperation]]
     * method invocation. */
    def defaultCloseOperationSet(operation: Int): IO[Unit] = IO {
      component.setDefaultCloseOperation(operation)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setTitle]]
     * method invocation. */
    def titleSet(title: String): IO[Unit] = IO {
      component.setTitle(title)
    }

    /** Returns an [[IO]] containing the code for maximizing the state of the JFrame wrapped by this instance. */
    def maximizedAndExtendedStateSet(): IO[Unit] =
      IO {
        component.setExtendedState(component.getExtendedState | Frame.MAXIMIZED_BOTH)
      }
  }

}