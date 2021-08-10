name := "CleanArch"

version := "0.1"

scalaVersion := "2.13.6"

scalacOptions ++= Seq("-language:implicitConversions", "-deprecation")

libraryDependencies ++= Seq("org.scalameta" %% "munit" % "0.7.27" % Test,
  "org.slf4j" % "slf4j-api" % "1.7.32",
  "org.slf4j" % "slf4j-simple" % "1.7.32", "org.scalatest" %% "scalatest" % "3.2.9", "org.specs" % "specs" % "1.4.3",
  "org.specs2" %% "specs2-core" % "4.12.3" % "test")

testFrameworks += new TestFramework("munit.Framework")

parallelExecution in Test := true