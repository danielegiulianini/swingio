package swingio.scala.examples

import java.awt.BorderLayout

import javax.swing.{JButton, JFrame, JLabel, JPanel, JSlider, WindowConstants}

import scala.concurrent.{ExecutionContext, Future, Promise}
import cats.effect.Async.fromFuture
import cats.effect.{ContextShift, IO}
import swingio.scala.examples.ExampleOfSimpleMVC.Model.{FINAL_MODEL_STATE, Model, modelUpdated}


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
      _ <- if (model.state < FINAL_MODEL_STATE) loop(updatedModel) else View.displayGameOver
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
    import swingio.scala.components.JButtonImplicits.JButtonIO
    import swingio.scala.components.ContainerImplicits.ContainerIO
    import swingio.scala.components.JFrameImplicits.JFrameIO
    import swingio.scala.components.ComponentImplicits.ComponentIO
    import swingio.scala.components.JLabelImplicits.JLabelIO
    import swingio.scala.components.JSliderImplicits.JSliderIO

    val frame = new JFrame("swing-io example")

    def welcome(): IO[Future[Unit]] = for {
      p <- Promise[Unit]()
      panel <- new JPanel
      button <- new JButton("start")
      _ <- monadicInvokeAndWait(for {
        cp <- frame.contentPane
        _ <- cp.added(panel, BorderLayout.CENTER)
        _ <- frame.sizeSet(320, 200)
        _ <- frame.defaultCloseOperationSet(WindowConstants.EXIT_ON_CLOSE)
        _ <- button.monadicActionListenerAdded(for {
          _ <- button.enabledSet(false)
          _ <- frame.getContentPane.allRemoved()
          _ <- frame.repainted()
          _ <- p.success()
        } yield ())
        _ <- panel.added(button)
        _ <- frame.visibleSet(true)
      } yield ())
    } yield p.future

    def read(): IO[Future[Int]] = for {
      p <- Promise[Int]()
      panel <- buildInputPanel(p)
      _ <- monadicInvokeAndWait(for {
        cp <- frame.contentPane
        _ <- cp.added(panel, BorderLayout.CENTER)
        _ <- frame.revalidated()
      } yield ())
    } yield p.future

    private def buildInputPanel(p: Promise[Int]): IO[JPanel] = for {
      panel <- new JPanel()
      input <- new JSlider()
      button <- new JButton("Hi, Update model with slider, please:")
      _ <- monadicInvokeAndWait(for {
        _ <- panel.add(input)
        _ <- button.monadicActionListenerAdded(for {
          _ <- button.enabledSet(false)
          _ <- frame.getContentPane.allRemoved
          _ <- frame.repainted()
          value <- input.valueGot
          _ <- p.success(value)
        } yield ())
        _ <- panel.added(button)
      } yield ())
    } yield panel

    def write(model: Model): IO[Unit] = for {
      panel <- new JPanel()
      output <- new JLabel("current model state: ")
      _ <- monadicInvokeAndWait(

        for {
        _ <- output.textSet("" + model.state)
        _ <- panel.added(output)
        cp <- frame.contentPane
        _ <- cp.added(panel, BorderLayout.SOUTH)
      } yield ())
    } yield ()

    def displayGameOver(): IO[Unit] = for {
      label <- new JLabel("game over.")
      cp <- frame.contentPane
      _ <- cp.added(label, BorderLayout.NORTH)
      _ <- frame.revalidated()
    } yield ()
  }

  Controller setUp Model.Model(0) unsafeRunSync
}
