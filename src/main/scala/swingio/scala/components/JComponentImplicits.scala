package swingio.scala.components

import java.awt.Dimension

import cats.effect.IO
import javax.swing.JComponent
import javax.swing.border.Border

object JComponentImplicits {

  implicit class JComponentIO(jComponent: JComponent) {

    /** Returns an [[IO]] containing the description of a [[JComponentImplicits#setMinimumSize]]
     * method invocation on this JComponent.*/
    def minimumSizeSet(dimension: Dimension): IO[Unit] = IO {jComponent.setMinimumSize(dimension)}

    /** Returns an [[IO]] containing the description of a [[JComponentImplicits#setMaximumSize]]
     * method invocation on this JComponent.*/
    def maximumSizeSet(dimension: Dimension): IO[Unit] = IO{ jComponent.setMaximumSize(dimension)}

    /** Returns an [[IO]] containing the description of a [[JComponentImplicits#setBorder]]
     * method invocation on this JComponent.*/
    def borderSet(border: Border): IO[Unit] = IO {jComponent.setBorder(border)}

  }
}
