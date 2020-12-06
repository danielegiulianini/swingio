package swingio.scala.components

import cats.effect.IO
import javax.swing.JLabel

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
