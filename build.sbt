name := "CleanArch"

version := "0.1"

scalaVersion := "2.12.12"

scalacOptions ++= Seq("-language:implicitConversions", "-deprecation")

libraryDependencies ++= Seq(
  // Logg
  "org.slf4j" % "slf4j-api" % "1.7.32",
  "org.slf4j" % "slf4j-simple" % "1.7.32",
  "ch.qos.logback" % "logback-classic" % "1.2.3",

  // Test
  "org.scalatest" %% "scalatest" % "3.2.9",
  "org.specs" % "specs" % "1.4.3",
  "org.specs2" %% "specs2-core" % "4.12.4-js-ec" % "test",
  "org.scalameta" %% "munit" % "0.7.27" % Test
)

testFrameworks += new TestFramework("munit.Framework")
