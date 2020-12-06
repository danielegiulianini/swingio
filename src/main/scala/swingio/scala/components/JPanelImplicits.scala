package swingio.scala.components

import cats.effect.IO
import javax.swing.JPanel
import javax.swing.plaf.PanelUI

object JPanelImplicits {

  implicit class JPanelIO (jPanel: JPanel) {


    def uiSet(ui:PanelUI): IO[Unit] = IO {	jPanel.setUI(ui)}

    
    def uiGot(): IO[PanelUI] = IO {jPanel.getUI}
  }

}
