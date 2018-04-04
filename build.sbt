name := "scraper"

version := "0.1"

scalaVersion := "2.12.5"

libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.5.0"
libraryDependencies += "net.ruippeixotog" %% "scala-scraper" % "2.1.0"
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
libraryDependencies += "com.typesafe" % "config" % "1.2.0"
libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.4.0"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0"
libraryDependencies += "nl.grons" %% "metrics4-scala" % "4.0.1"

