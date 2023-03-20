lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "br.com.danilooa.learning.scalatest",
      scalaVersion := "2.13.6"
    )),
    name := "learning_scalatest"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test
libraryDependencies += "org.scalatestplus" %% "scalatestplus-scalacheck" % "1.0.0-M2" % Test
libraryDependencies += "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test


