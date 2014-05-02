name := "cfg"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  "ws.securesocial" %% "securesocial" % "2.1.3",
  cache
)


libraryDependencies += "postgresql" % "postgresql" % "9.3-101-1.jdbc4"

resolvers += Resolver.sonatypeRepo("releases")

play.Project.playJavaSettings
