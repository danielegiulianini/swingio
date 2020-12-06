package swingio.scala.components

import java.awt.Dimension

import cats.effect.IO
import javax.swing.JComponent
import javax.swing.border.Border

object JComponent {
  implicit class JComponentIO(jComponent: JComponent) {


    def minimumSizeSet(dimension: Dimension): IO[Unit] = IO {jComponent.setMinimumSize(dimension)}


    def maximumSizeSet(dimension: Dimension): IO[Unit] = IO{ jComponent.setMaximumSize(dimension)}

    
    def borderSet(border: Border): IO[Unit] = IO {jComponent.setBorder(border)}
  }
}
