package swingio.scala.components

import cats.effect.IO
import javax.swing.JLabel

object JLabelImplicits {

  implicit class JLabelIO(jLabel: JLabel) {

    /** Returns an [[IO]] containing the description of a [[JLabel#setText]]
     * method invocation on the [[JLabel]] wrapped in this instance. */
    def setText(text: String): IO[Unit] = IO {jLabel.setText(text)}

    /** Returns an [[IO]] containing the description of a [[JLabel#getText]]
     * method invocation on the [[JLabel]] wrapped in this instance. */
    def getText: IO[String] = IO {jLabel.getText}
  }

}
