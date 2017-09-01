logLevel := sbt.Level.Warn

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.4")
addSbtPlugin("no.arktekk.sbt" % "aether-deploy" % "0.19.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.0")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.12.0")