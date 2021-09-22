name := "CleanArch"

version := "0.1"

scalaVersion := "2.12.12"

scalacOptions ++= Seq("-language:implicitConversions", "-deprecation")

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.6",
  "com.zaxxer" % "HikariCP" % "4.0.3",
  "org.scalikejdbc" %% "scalikejdbc" % "3.4.2",
  "org.postgresql" % "postgresql" % "42.2.23",
  "org.slf4j" % "slf4j-api" % "1.7.32",
  "org.slf4j" % "slf4j-simple" % "1.7.32",
  "org.scalatest" %% "scalatest" % "3.2.9",
  "org.scalameta" %% "munit" % "0.7.27" % Test,
  "ch.qos.logback" % "logback-core" % "1.2.6")

testFrameworks += new TestFramework("munit.Framework")
