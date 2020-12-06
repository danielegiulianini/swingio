package swingio.scala.components

import cats.effect.IO
import javax.swing.JPanel
import javax.swing.plaf.PanelUI

trait JPanelImplicits {
  
  implicit class JPanelIO (jPanel: JPanel) {

    /** Returns an [[IO]] containing the description of a [[JPanel#setUI]]
     * method invocation on this [[JPanel]]. */
    def uiSet(ui:PanelUI): IO[Unit] = IO {	jPanel.setUI(ui)}

    /** Returns an [[IO]] containing the description of a [[JPanel#getUI]]
     * method invocation on this [[JPanel]]. */
    def uiGot(): IO[PanelUI] = IO {jPanel.getUI}
  }

}
