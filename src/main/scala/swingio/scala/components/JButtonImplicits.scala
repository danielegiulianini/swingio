package swingio.scala.components

import java.awt.event.ActionListener

import cats.effect.IO
import javax.swing.JButton


trait JButtonImplicits {

  class JButtonIO(jButton: JButton){

    /** Returns an [[IO]] containing the description of a [[JButton#getText]]
     * method invocation.*/
    def getText(): IO[String] = IO {jButton.getText}

    /** Returns an [[IO]] containing the description of a [[JButton#setText]]
     * method invocation.*/
    def setText(text: String): IO[Unit] = IO {jButton.setText(text)}

    /** Returns an [[IO]] containing the description of a [[JButton#setEnabled]]
     * method invocation.*/
    def setEnabled(b: Boolean): IO[Unit] = IO { jButton.setEnabled(b) }

    /** Returns an [[IO]] that registers an [[ActionListener]] for handling the
     * [[java.awt.event.ActionEvent]]s generated by the JButton wrapped by this instance.
     * It accepts as parameter a procedural specification of the listener instead of a
     * monadic one.
     * @see [[MonadicActionListener]] and [[monadicActionListenerAdded]] for a monadic
     *      listener description.*/
    def addActionListener(l: ActionListener): IO[Unit] = IO {jButton.addActionListener(l)}

    /** Returns an [[IO]] containing the code for unregistering the given [[ActionListener]] from
     * the JButton wrapped by this instance. */
    def removeActionListener(l:ActionListener): IO[Unit] = IO {jButton.removeActionListener(l)}

  }
}