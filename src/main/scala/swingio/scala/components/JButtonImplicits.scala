package swingio.scala.components

import java.awt.event.ActionListener

import cats.effect.IO
import javax.swing.JButton


object JButtonImplicits {

  class JButtonIO(jButton: JButton){


    def textGot(): IO[String] = IO {jButton.getText}


    def textSet(text: String): IO[Unit] = IO {jButton.setText(text)}


    def enabledSet(b: Boolean): IO[Unit] = IO { jButton.setEnabled(b) }


    def actionListenerAdded(l: ActionListener): IO[Unit] = IO {jButton.addActionListener(l)}

   
    def actionListenerRemoved(l:ActionListener): IO[Unit] = IO {jButton.removeActionListener(l)}

  }
}
