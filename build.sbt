import sbt.Keys._
import sbt._
import sbtrelease.Version

name := "serverless-ammonite"

resolvers += Resolver.sonatypeRepo("public")
scalaVersion := "2.12.6"
releaseNextVersion := { ver =>
  Version(ver).map(_.bumpMinor.string).getOrElse("Error")
}
assemblyJarName in assembly := "serverless-ammonite.jar"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-lambda-java-events" % "2.1.0",
  "com.amazonaws" % "aws-lambda-java-core"   % "1.2.0",
  "com.lihaoyi"   % "ammonite"               % "1.1.2" cross CrossVersion.full
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings"
)
