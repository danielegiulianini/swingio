package swingio.scala.concurrency

import cats.effect.IO
import javax.swing.SwingUtilities

trait ConcurrencyUtilities {
  /*type MonadicRunnable = () => IO[Unit]
    def invokeAndWait(monadicRunnable: MonadicRunnable) =
      SwingUtilities.invokeAndWait(() => monadicRunnable.unsafeRunSync())*/

  /**
   * Returns the [[IO]] containing the code for asynchronously executing the computation described inside the
   * given IO parameter on the AWT event dispatching thread, in a thread-safe manner.
   * This method should be used whenever a thread other than EDT needs to asynchronously update the GUI.
   * Unlike [[monadicInvokeAndWait()]], upon calling "unsafeRunSync" on the IO returned, the control flow
   * will immediately return from the [[SwingUtilities#invokeLater]] call wrapped in the IO returned and
   * the GUI-related code will be executed after all pending AWT events have been processed.
   * The actual execution of the [[IO]] returned should not happen on the EDT thread without a proper
   * thread or context-shifting mechanism.
   *
   * @param byNameMonadicRunnable the [[IO]] containing the GUI-related logic to be executed.
   * @return an IO wrapping the [[SwingUtilities#invokeLater]] thread-safe call.
   */
  def monadicInvokeLater(byNameMonadicRunnable: =>IO[_]): IO[Unit] =
    IO{ SwingUtilities.invokeLater(() => byNameMonadicRunnable.unsafeRunSync()) }

  /**
   * Returns the [[IO]] containing the code for synchronously executing the computation described inside the
   * computation parameter on the AWT event dispatching thread, in a thread-safe manner.
   * This method should be used whenever a thread other than EDT needs to update the GUI.
   * Unlike [[monadicInvokeLater]], upon calling "unsafeRunSync" on the IO returned, the control flow
   * will block until all pending AWT events have been processed and only then the
   * [[SwingUtilities#invokeLater]] wrapped call will return.
   * If searching for an asynchronous execution of [[IO]]'s content, must use [[monadicInvokeLater]] instead.
   *
   * @see [[swingio.scala.examples.ThreadSafeExampleWithLayoutWithSwingIo]] for examples of how to use it
   *      inside a monadic chain.
   * @param byNameMonadicRunnable the [[IO]] containing the GUI-related logic to be executed.
   * @return an IO wrapping the [[SwingUtilities#invokeAndWait]] thread-safe call.
   */
  def monadicInvokeAndWait(byNameMonadicRunnable: =>IO[_]): IO[Unit] =
    IO{ SwingUtilities.invokeAndWait(() => byNameMonadicRunnable.unsafeRunSync()) }

}
