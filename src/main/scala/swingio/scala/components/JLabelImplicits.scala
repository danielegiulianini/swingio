package swingio.scala.components

import cats.effect.IO
import javax.swing.JLabel

object JLabelImplicits {

  implicit class JLabelIO(jLabel: JLabel) {

    
    def setText(text: String): IO[Unit] = IO {jLabel.setText(text)}


    def getText: IO[String] = IO {jLabel.getText}
  }

}
