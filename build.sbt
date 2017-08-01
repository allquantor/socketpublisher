name := """socketpublisher"""

version := "0.1"

scalaVersion := "2.12.3"

crossScalaVersions := Seq("2.11.8", "2.12.3")

scalacOptions := Seq("-unchecked",
  "-feature",
  "-deprecation",
  "-encoding",
  "utf8")


resourceDirectory in Compile := baseDirectory.value / "resources"

libraryDependencies ++= {
  val akkaV = "2.5.0"
  val akkaHttpV = "10.0.6"
  Seq(
    "com.typesafe.akka" %% "akka-stream" % akkaV,

    "com.typesafe.akka" %% "akka-testkit" % akkaV,

    // https://mvnrepository.com/artifact/com.typesafe.akka/akka-stream-testkit_2.11
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaV,

    // https://mvnrepository.com/artifact/com.typesafe.akka/akka-http_2.11
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,

    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,

    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV
  )
}

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "io.allquantor",
  scalaVersion := "2.12.3",
  test in assembly := {}
)

lazy val app = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    mainClass in assembly := Some("io.allquantor.Main")
  )
