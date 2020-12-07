package swingio.scala.concurrency

import cats.effect.IO
import javax.swing.SwingUtilities

trait ConcurrencyUtilities {
  /*type MonadicRunnable = () => IO[Unit]
    def invokeAndWait(monadicRunnable: MonadicRunnable) =
      SwingUtilities.invokeAndWait(() => monadicRunnable.unsafeRunSync())*/

  def monadicInvokeLater(byNameMonadicRunnable: =>IO[Unit]): IO[Unit] =
    IO{ SwingUtilities.invokeLater(() => byNameMonadicRunnable.unsafeRunSync()) }

  def monadicInvokeAndWait(byNameMonadicRunnable: =>IO[Unit]): IO[Unit] =
    IO{ SwingUtilities.invokeAndWait(() => byNameMonadicRunnable.unsafeRunSync()) }

}
