name := "shapes-visitor-java-sbt"

version := "0.1"

javacOptions in compile += "-Xlint:all"

javaOptions += "-enableassertions"

libraryDependencies ++= Seq(
  "org.apache.commons" %  "commons-collections4" % "4.4",
  "com.googlecode.json-simple" % "json-simple" % "1.1.1",
  "com.novocode"       %  "junit-interface"      % "0.11" % Test
)

enablePlugins(JavaAppPackaging)
