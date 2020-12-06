package swingio.scala.components

import java.awt.{Component, Container, Frame}

import cats.effect.IO
import javax.swing.JFrame


object JFrameImplicits {

  implicit class JFrameIO(jFrame: JFrame) {

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#getContentPane]]
     * method invocation. */
    def getContentPane(): IO[Container] = IO { jFrame.getContentPane() }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setResizable]]
     * method invocation. */
    def setResizable(resizable: Boolean): IO[Unit] = IO {
      jFrame.setResizable(resizable)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setVisible]]
     * method invocation. */
    def setVisible(b: Boolean): IO[Unit] = IO {
      jFrame.setVisible(b)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#pack]]
     * method invocation. */
    def pack(): IO[Unit] = IO {
      jFrame.pack()
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setSize]]
     * method invocation. */
    def setSize(width: Int, height: Int): IO[Unit] = IO {
      jFrame.setSize(width, height)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setLocationRelativeTo]]
     * method invocation. */
    def setLocationRelativeTo(c: Component): IO[Unit] = IO {
      jFrame.setLocationRelativeTo(c)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setDefaultCloseOperation]]
     * method invocation. */
    def setDefaultCloseOperation(operation: Int): IO[Unit] = IO {
      jFrame.setDefaultCloseOperation(operation)
    }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setTitle]]
     * method invocation. */
    def setTitle(title: String): IO[Unit] = IO {
      jFrame.setTitle(title)
    }

    /** Returns an [[IO]] containing the code for maximizing the state of the JFrame wrapped by this instance. */
    def setExtendedState(): IO[Unit] =
      IO {
        jFrame.setExtendedState(jFrame.getExtendedState | Frame.MAXIMIZED_BOTH)
      }
  }

}