package swingio.scala.components

import cats.effect.IO
import javax.swing.JPanel
import javax.swing.plaf.PanelUI

/** Contains implicit class that enriches [[JPanel]] with additional functionalities by leveraging what is
 * referred to as "Pimp My Library Pattern".
 * Use this imports:
 * {{{
 * import swingio.scala._
 * import swingio.scala.components.JPanelImplicits._
 * }}}
 * to enable methods returning [[IO]] for combining methods invocation inside for-comprehension and
 * providing a description of GUI-related logic.
 *
 * @see [[IO]] for details on IO monad and how to run it. */
object JPanelImplicits {
  
  implicit class JPanelIO (jPanel: JPanel) {

    /** Returns an [[IO]] containing the description of a [[JPanel#setUI]]
     * method invocation on this [[JPanel]]. */
    def uiSet(ui:PanelUI): IO[Unit] = IO {	jPanel.setUI(ui)}

    /** Returns an [[IO]] containing the description of a [[JPanel#getUI]]
     * method invocation on this [[JPanel]]. */
    def uiGot(): IO[PanelUI] = IO {jPanel.getUI}
  }

}
