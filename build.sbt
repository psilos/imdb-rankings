name := "bgc"

version := "0.1"

scalaVersion := "2.12.10"

resolvers += "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % Versions.Spark ,

  "com.github.pureconfig" %% "pureconfig" % Versions.PureConfig,
  "org.slf4j" % "log4j-over-slf4j" % Versions.SLF4J,
  "ch.qos.logback" % "logback-classic" % Versions.Logback,
  "com.typesafe.scala-logging" %% "scala-logging" % Versions.TypesafeLogging,
  "org.scalatest"     %% "scalatest"            % Versions.ScalaTest % Test,
  "MrPowers" % "spark-fast-tests" % Versions.SparkFastTests,
)
excludeDependencies := Seq(
  "org.slf4j" % "slf4j-log4j12",
  "log4j",
  "org.apache.logging.log4j"
)