name := "UTXmlConverter"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

mainClass in assembly := Some("net.white_azalea.Application")

assemblyJarName in assembly := "ut_converter.jar"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "org.specs2" %% "specs2-core" % "3.9.0" % "test",
  "com.github.scopt" %% "scopt" % "3.6.0"
)

scalacOptions in Test ++= Seq("-Yrangepos")
