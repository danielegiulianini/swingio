package swingio.scala.components

import java.awt.{Component, Container, LayoutManager}

import cats.effect.IO

trait ContainerImplicits {
  implicit class ContainerIO(container: Container){

    /** Returns an [[IO]] containing the code for adding the [[ComponentImplicits]] to this container.
     * It is the monadic counterpart of [[ContainerImplicits#added]].*/
    def add(componentToBeAdded: Component): IO[Component] = IO {container.add(componentToBeAdded)}

    /** Returns an [[IO]] containing the description of a [[java.awt.Container#add]]
     * method invocation. It is the monadic counterpart of [[ContainerImplicits#added]].
     * After adding the specified component at the end of this container, it also notifies
     * the layout manager to add the component to this container's layout using the
     * specified constraints object. */
    def add(componentToBeAdded: Component, constraints: Object): IO[Unit] =
      IO {container.add(componentToBeAdded, constraints)}

    /** Returns an [[IO]] containing the description of a [[java.awt.Container#remove]]
     * method invocation on this container.*/
    def removed(componentToBeAdded: Component): IO[Unit] =
      IO { container.remove(componentToBeAdded) }

    /** Returns an [[IO]] containing the description of a [[java.awt.Container#removeAll]]
     * method invocation on this container.*/
    def removeAll(): IO[Unit] = IO { container.removeAll() }

    /** Returns an [[IO]] containing the description of a [[java.awt.Container#setLayout]]
     * method invocation on this container.*/
    def layoutSet(mgr : LayoutManager): IO[Unit] = IO { container.setLayout(mgr) }
  }
}
