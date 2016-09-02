name := "cc2csv"

version in ThisBuild := "0.0.2"

organization in ThisBuild := "jfalkner"

scalaVersion in ThisBuild := "2.11.8"

libraryDependencies ++= Seq(
  // needed if using this API
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  // needed only for running the tests
  "org.specs2" % "specs2_2.11" % "2.4.1-scalaz-7.0.6" % "test"
)