package swingio.scala.components

import java.awt.{Component, Container, LayoutManager}

import cats.effect.IO

/** Contains implicit class that enriches [[Container]] with additional functionalities by leveraging what is
 * referred to as "Pimp My Library Pattern".
 * Use this import:
 * {{{
 * import swingio.scala._
 * import swingio.scala.components.ContainerImplicits._
 * }}}
 * to enable methods returning [[IO]] for combining methods invocation inside for-comprehension and
 * providing a description of GUI-related logic.
 *
 * @see [[IO]] for details on IO monad and how to run it. */
object ContainerImplicits {

  implicit class ContainerIO(container: Container){

    /** Returns an [[IO]] containing the code for adding the [[ComponentImplicits]] to this container.
     * It is the monadic counterpart of [[ContainerImplicits#added]].*/
    def added(componentToBeAdded: Component): IO[Component] = IO {container.add(componentToBeAdded)}

    /** Returns an [[IO]] containing the description of a [[java.awt.Container#add]]
     * method invocation on this container. It is the monadic counterpart of [[ContainerImplicits#added]].
     * After adding the specified component at the end of this container, it also notifies
     * the layout manager to add the component to this container's layout using the
     * specified constraints object. */
    def added(componentToBeAdded: Component, constraints: Object): IO[Unit] =
      IO {container.add(componentToBeAdded, constraints)}

    /** Returns an [[IO]] containing the description of a [[java.awt.Container#remove]]
     * method invocation on this container.*/
    def removed(componentToBeAdded: Component): IO[Unit] =
      IO { container.remove(componentToBeAdded) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Container#removeAll]]
     * method invocation on this container.*/
    def allRemoved(): IO[Unit] = IO { container.removeAll() }

    /** Returns an [[IO]] containing the description of a [[java.awt.Container#setLayout]]
     * method invocation on this container.*/
    def layoutSet(mgr : LayoutManager): IO[Unit] = IO { container.setLayout(mgr) }
  }
}
