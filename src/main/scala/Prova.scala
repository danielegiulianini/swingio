import javax.swing.{JButton, JFrame, JPanel}

object Prova extends App {
  import swingio.scala._

  val program = for {
    jf <- new JFrame()
    jp <- new JPanel
    _ <- jf.add(jp)
    _ <- jf.setSize(320, 300)
    //_ <- SwingUtilities.invokeAndWait(for{      _ <- println("ciao")    } yield ())
    b <- new JButton("cua")
    _ <- jp.add(b)
    _ <- jf.setVisible(true)
  } yield ()

  program unsafeRunSync
}
