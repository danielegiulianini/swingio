package swingio.scala.components

import java.awt.{Component, Container, Frame}

import cats.effect.IO
import javax.swing.JFrame

/** Contains implicit class that enriches [[JFrame]] with additional functionalities by leveraging what is
 * referred to as "Pimp My Library Pattern".
 * Use this imports:
 * {{{
 * import swingio.scala._
 * import swingio.scala.components.JFrameImplicits._
 * }}}
 * to enable methods returning [[IO]] for combining methods invocation inside for-comprehension and
 * providing a description of GUI-related logic.
 *
 * @see [[IO]] for details on IO monad and how to run it. */
object JFrameImplicits {

  implicit class JFrameIO(jFrame: JFrame) {

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#getContentPane]]
     * method invocation on this JFrame. */
    def contentPane(): IO[Container] = IO { jFrame.getContentPane }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setResizable]]
     * method invocation on this JFrame. */
    def resizableSet(resizable: Boolean): IO[Unit] = IO { jFrame.setResizable(resizable) }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setVisible]]
     * method invocation on this JFrame. */
    def visibleSet(b: Boolean): IO[Unit] = IO { jFrame.setVisible(b) }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#pack]]
     * method invocation on this JFrame. */
    def packed(): IO[Unit] = IO { jFrame.pack() }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setSize]]
     * method invocation on this JFrame. */
    def sizeSet(width: Int, height: Int): IO[Unit] = IO { jFrame.setSize(width, height) }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setLocationRelativeTo]]
     * method invocation on this JFrame. */
    def locationRelativeTo(c: Component): IO[Unit] = IO { jFrame.setLocationRelativeTo(c) }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setDefaultCloseOperation]]
     * method invocation on this JFrame. */
    def defaultCloseOperationSet(operation: Int): IO[Unit] = IO { jFrame.setDefaultCloseOperation(operation) }

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#setTitle]]
     * method invocation on this JFrame. */
    def titleSet(title: String): IO[Unit] = IO { jFrame.setTitle(title) }

    /** Returns an [[IO]] containing the code for maximizing the state of this JFrame. */
    def extendedStateSet(): IO[Unit] = IO {
        jFrame.setExtendedState(jFrame.getExtendedState | Frame.MAXIMIZED_BOTH)
      }
  }

}