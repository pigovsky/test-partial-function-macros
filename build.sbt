lazy val root = (project in file("."))
  .settings(
    name := "test-partial-function-macros",
    version := "0.1"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.scala-lang" % "scala-reflect" % "2.11.12"
)
scalaVersion := "2.11.12"
