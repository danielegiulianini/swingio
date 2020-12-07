package swingio.scala.concurrency

import cats.effect.IO
import javax.swing.SwingUtilities

trait ConcurrencyUtilities {
  /*type MonadicRunnable = () => IO[Unit]
    def invokeAndWait(monadicRunnable: MonadicRunnable) =
      SwingUtilities.invokeAndWait(() => monadicRunnable.unsafeRunSync())*/

  /**
   * Returns the [[IO]] containing the code for synchronously executing the computation described inside the
   * computation parameter on the AWT event dispatching thread, in a thread-safe manner.
   * This method should be used whenever a thread other than EDT needs to update the GUI.
   * Unlike [[invokingLater]], upon calling "unsafeRunSync" on the IO returned, the control flow
   * will block until all pending AWT events have been processed and only then the
   * [[SwingUtilities#invokeLater]] wrapped call will return.
   * If searching for an asynchronous execution of [[IO]]'s content, must use [[monadicInvokeLater]] instead.
   *
   * @see TODO for examples of how to use it
   *      inside a monadic chain.
   * @param byNameMonadicRunnable the [[IO]] containing the GUI-related logic to be executed.
   * @return an IO wrapping the [[SwingUtilities#invokeAndWait]] thread-safe call.
   */
  def monadicInvokeLater(byNameMonadicRunnable: =>IO[Unit]): IO[Unit] =
    IO{ SwingUtilities.invokeLater(() => byNameMonadicRunnable.unsafeRunSync()) }

  
  def monadicInvokeAndWait(byNameMonadicRunnable: =>IO[Unit]): IO[Unit] =
    IO{ SwingUtilities.invokeAndWait(() => byNameMonadicRunnable.unsafeRunSync()) }

}
