name := "kafka-statsd"
scalaVersion := "2.11.8"
organization := "com.zefr.dp"
version := "1.0"

scalaVersion := "2.11.8"

val kafkaVersion = "0.11.0.0"
val artifactory = "https://zefr.jfrog.io/zefr"
val confluentVersion = ""

resolvers += "Artifactory Maven Releases" at s"$artifactory/libs-releases"
resolvers += "Artifactory SBT Releases" at s"$artifactory/sbt-releases"
resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += "Artifactory Maven Snapshots" at s"$artifactory/libs-snapshots"
resolvers += "Artifactory SBT Snapshots" at s"$artifactory/sbt-snapshots"
resolvers +=  "Confluent repository" at "http://packages.confluent.io/maven/"

//val mavenLocal = "Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository"
//resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-log4j12" % "1.7.21",
  "com.datadoghq" % "java-dogstatsd-client" % "2.3",
  "org.apache.kafka" % "kafka-clients" % kafkaVersion,
  "org.apache.kafka" % "kafka-streams" % "0.11.0.0",
  "org.apache.kafka" %% "kafka" % kafkaVersion exclude ("org.slf4j","slf4j-log4j12") exclude("log4j", "log4j"),
  //"org.coursera" % "dropwizard-metrics-datadog" % "1.1.13",
  "org.easymock" % "easymock" % "3.2",
  "org.mockito" % "mockito-core"  % "2.8.9"
)


////////////////////////////
// publish to artifactory //
////////////////////////////


// http://www.scala-sbt.org/0.13/docs/Using-Sonatype.html
publishMavenStyle := true

publishTo := {
  if (isSnapshot.value)
    Some("Artifactory Realm" at s"$artifactory/libs-snapshot")
  else
    Some("Artifactory Realm"  at s"$artifactory/libs-release")
}
credentials ++= {
  for {
    username <- Option(System.getenv().get("ARTIFACTORY_USERNAME"))
    password <- Option(System.getenv().get("ARTIFACTORY_PASSWORD"))
  } yield Credentials("Artifactory Realm", "zefr.jfrog.io", username, password)
}.toSeq