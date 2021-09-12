name := "CleanArch"

version := "0.1"

scalaVersion := "2.12.12"

scalacOptions ++= Seq("-language:implicitConversions", "-deprecation")

libraryDependencies ++= Seq(
  "com.zaxxer" % "HikariCP" % "5.0.0",
  "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
  "org.postgresql" % "postgresql" % "42.2.23",
  "org.slf4j" % "slf4j-api" % "1.7.32",
  "org.slf4j" % "slf4j-simple" % "1.7.32",
  "org.scalatest" %% "scalatest" % "3.2.9",
  "org.specs" % "specs" % "1.4.3",
  "org.specs2" %% "specs2-core" % "4.12.9" % "test",
  "org.scalameta" %% "munit" % "0.7.27" % Test)

testFrameworks += new TestFramework("munit.Framework")
