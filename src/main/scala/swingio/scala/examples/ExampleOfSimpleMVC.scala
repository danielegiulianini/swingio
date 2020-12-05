package swingio.scala.examples

import java.awt.BorderLayout

import cats.effect.Async.fromFuture
import cats.effect.{ContextShift, IO}
import javax.swing._
import swingio.scala.ThreadingUtilities.Monadic.monadicInvokeAndWait

import scala.concurrent.{ExecutionContext, Future, Promise}

object ExampleOfSimpleMVC extends App {
  implicit val contextShift: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  object Controller {

    def setUp(model: Model): IO[Unit] = for {
      _ <- fromFuture(View.welcome())
      _ <- loop(model)
    } yield ()

    def loop(model: Model): IO[Unit] = for {
      input <- fromFuture(View.read())
      updatedModel <- modelUpdated(model, input)
      _ <- View.write(updatedModel)
      _ <- if (model.state < FINAL_MODEL_STATE) loop(updatedModel) else IO {
        print("gameOver")
      }
    } yield ()
  }

  object Model {
    val STARTING_MODEL_STATE = 0
    val FINAL_MODEL_STATE = 100

    case class Model(state: Int)

    def modelUpdated(model: Model, input: Int): IO[Model] = IO {
      Model(model.state + input)
    }
  }

  object View {

    import swingio.scala._

    val frame = new JFrame("swing-io example")

    def welcome(): IO[Future[Unit]] = for {
      p <- Promise[Unit]()
      panel <- new JPanel
      button <- new JButton("start")
      _ <- monadicInvokeAndWait(for {
        _ <- frame.getContentPane.add(panel, BorderLayout.CENTER)
        _ <- frame.setSize(320, 200)
        _ <- frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
        _ <- button.addMonadicActionListener(_ => for {
          _ <- button.setEnabled(false)
          _ <- frame.getContentPane.removeAll()
          _ <- frame.repaint()
          _ <- p.success()
        } yield ())
        _ <- panel.add(button)
        _ <- frame.setVisible(true)
      } yield ())
    } yield p.future

    def read(): IO[Future[Int]] = for {
      p <- Promise[Int]()
      panel <- buildInputPanel(p)
      _ <- monadicInvokeAndWait(for {
        _ <- frame.getContentPane.add(panel, BorderLayout.CENTER)
        _ <- frame.revalidate()
      } yield ())
    } yield p.future

    private def buildInputPanel(p: Promise[Int]): IO[JPanel] = for {
      panel <- new JPanel()
      input <- new JSlider()
      button <- new JButton("Hi, Update model with slider, please:")
      _ <- monadicInvokeAndWait(for {
        _ <- panel.add(input)
        _ <- button.addMonadicActionListener(_ => for {
          _ <- button.setEnabled(false)
          _ <- p.success(input.getValue)
          _ <- frame.getContentPane.removeAll
          _ <- frame.revalidate
        } yield ())
        _ <- panel.add(button)
      } yield ())
    } yield panel

    def write(model: Model): IO[Unit] = for {
      panel <- new JPanel()
      output <- new JLabel("current model state: ")
      _ <- monadicInvokeAndWait(for {
        _ <- output.setText("" + model.state)
        _ <- panel.add(output)
        _ <- frame.getContentPane.add(panel, BorderLayout.SOUTH)
      } yield ())
    } yield ()
  }

  Controller setUp Model.Model(0) unsafeRunSync
}
