name := "Merchant's-Guide-to-the-Galaxy"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-deprecation")

libraryDependencies += "junit" % "junit" % "4.12"

resolvers ++= Seq(
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Scala-Tools Snapshots" at "http://scala-tools.org/repo-snapshots",
  "Scala Tools Releases" at "http://scala-tools.org/repo-releases"
)


