package swingio.scala.components

import java.awt.{Component, Container, Frame}

import cats.effect.IO
import javax.swing.JFrame


object JFrameImplicits {

  implicit class JFrameIO(jFrame: JFrame) {

    /** Returns an [[IO]] containing the description of a [[JFrameImplicits#getContentPane]]
     * method invocation. */
    def contentPane(): IO[Container] = IO { jFrame.getContentPane() }


  }

}