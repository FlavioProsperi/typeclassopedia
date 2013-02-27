name := "scalaz-lite"

version := "1.0"

scalaVersion := "2.10.0"

scalaBinaryVersion := "2.10"

resolvers ++= Seq(
    "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
    "releases"  at "http://oss.sonatype.org/content/repositories/releases")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

unmanagedSourceDirectories in Compile := Seq(file("src/main/scala") )

unmanagedSourceDirectories in Test := Seq(file("src/test/scala") )