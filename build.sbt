name := "cfg"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  "ws.securesocial" %% "securesocial" % "2.1.3",
  cache
)

resolvers += Resolver.sonatypeRepo("releases")

play.Project.playJavaSettings
