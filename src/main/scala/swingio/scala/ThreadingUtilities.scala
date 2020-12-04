package swingio.scala

import cats.effect.IO
import javax.swing.SwingUtilities

object ThreadingUtilities {
  implicit def fromByNameUnitToRunnable(fa : => Unit): Runnable = () => fa

  //implicit def fromMonadicByNameUnitToRunnable(fa : => IO[Unit]): Runnable = () => fa.unsafeRunSync()
  object Monadic {
    /*type MonadicRunnable = () => IO[Unit]
    def invokeAndWait(monadicRunnable: MonadicRunnable) =
      SwingUtilities.invokeAndWait(() => monadicRunnable.unsafeRunSync())*/


    def monadicInvokeLater(byNameMonadicRunnable: =>IO[Unit]): Unit =
      SwingUtilities.invokeLater(() => byNameMonadicRunnable.unsafeRunSync())

    def monadicInvokeAndWait(byNameMonadicRunnable: =>IO[Unit]): Unit =
      SwingUtilities.invokeAndWait(() => byNameMonadicRunnable.unsafeRunSync())

  }
}
