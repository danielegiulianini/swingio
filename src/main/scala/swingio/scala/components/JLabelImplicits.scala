package swingio.scala.components

import cats.effect.IO
import javax.swing.JLabel

/** Contains implicit class that enriches [[JLabel]] with additional functionalities by leveraging what is
 * referred to as "Pimp My Library Pattern".
 * Use this imports:
 * {{{
 * import swingio.scala._
 * import swingio.scala.components.JLabelImplicits._
 * }}}
 * to enable methods returning [[IO]] for combining methods invocation inside for-comprehension and
 * providing a description of GUI-related logic.
 *
 * @see [[IO]] for details on IO monad and how to run it. */
object JLabelImplicits {

  implicit class JLabelIO(jLabel: JLabel) {

    /** Returns an [[IO]] containing the description of a [[JLabel#setText]]
     * method invocation on this instance. */
    def textSet(text: String): IO[Unit] = IO {jLabel.setText(text)}

    /** Returns an [[IO]] containing the description of a [[JLabel#getText]]
     * method invocation on this instance. */
    def textGot: IO[String] = IO {jLabel.getText}
  }

}
