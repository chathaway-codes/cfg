name := "cfg"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  "be.objectify" %% "deadbolt-java" % "2.2-RC4",
  "ws.securesocial" %% "securesocial" % "2.1.3",
  cache
)


resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.sonatypeRepo("releases")

resolvers += Resolver.url("Objectify Play Repository - snapshots", url("http://schaloner.github.com/snapshots/"))(Resolver.ivyStylePatterns)

play.Project.playJavaSettings
