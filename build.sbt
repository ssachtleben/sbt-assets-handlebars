sbtPlugin := true

organization := "com.ssachtleben"

name := """sbt-assets-handlebars"""

version := "1.0.0"

scalaVersion := "2.10.4"

scalacOptions += "-feature"

autoScalaLibrary := false

crossPaths := false

publishArtifact in(Compile, packageDoc) := false

resolvers ++= Seq(
  "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.url("sbt snapshot plugins", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns),
  Resolver.sonatypeRepo("snapshots"),
  "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/",
  Resolver.mavenLocal
)

libraryDependencies ++= Seq(
  "org.webjars" % "requirejs-node" % "2.1.17",
  "org.webjars" % "handlebars" % "3.0.0-1",
  "org.webjars" % "mkdirp" % "0.3.5"
)


addSbtPlugin("com.typesafe.sbt" % "sbt-js-engine" % "1.0.0")

scriptedSettings

scriptedLaunchOpts <+= version apply { v => s"-Dproject.version=$v" }