name := "cfg"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  "ws.securesocial" %% "securesocial" % "2.1.3",
  cache
)


libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1101-jdbc4"

resolvers += Resolver.sonatypeRepo("releases")

play.Project.playJavaSettings
