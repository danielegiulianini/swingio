name := "swingIO"

version := "0.1"

scalaVersion := "2.12.11"


scalacOptions += "-Ypartial-unification"
scalacOptions += "-feature"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "2.2.0"