package swingio.scala.components

import java.awt.{Component, Container}

import cats.effect.IO

object Container {
  implicit class ContainerIO(container: Container){

    /** Returns an [[IO]] containing the code for adding the [[Component]] wrapped in the given [[ComponentIO]] to
     * the [[Container]] wrapped in this instance. It is the monadic counterpart of [[Container#added]].*/
    def add(componentToBeAdded: Component): IO[Component] = IO {container.add(componentToBeAdded)}

    /** Returns an [[IO]] containing the description of a [[java.awt.Container#add]]
     * method invocation. It is the monadic counterpart of [[Container#added]].
     * After adding the specified component at the end of this container, it also notifies
     * the layout manager to add the component to this container's layout using the
     * specified constraints object. */
    def add(componentToBeAdded: Component, constraints: Object): IO[Unit] =
      IO {container.add(componentToBeAdded, constraints)}


    /** Returns an [[IO]] containing the description of a [[java.awt.Container#remove]]
     * method invocation.*/
    def removed(componentToBeAdded: Component): IO[Unit] =
      IO { container.remove(componentToBeAdded)  }

    
  }
}
