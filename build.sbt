name := """play-java-spring"""

version := "1.0-SNAPSHOT"

resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("Objectify Play Snapshot Repository", url("http://schaloner.github.com/snapshots/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("play-easymail (release)", url("http://joscha.github.com/play-easymail/repo/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("play-easymail (snapshot)", url("http://joscha.github.com/play-easymail/repo/snapshots/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("play-authenticate (release)", url("http://joscha.github.com/play-authenticate/repo/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("play-authenticate (snapshot)", url("http://joscha.github.com/play-authenticate/repo/snapshots/"))(Resolver.ivyStylePatterns)


libraryDependencies ++= Seq(
  javaCore,
  "com.h2database" % "h2" % "1.3.168",
  "org.springframework" % "spring-context" % "3.2.1.RELEASE",
  "org.springframework" % "spring-orm" % "3.2.1.RELEASE",
  "org.springframework" % "spring-jdbc" % "3.2.1.RELEASE",
  "org.springframework" % "spring-tx" % "3.2.1.RELEASE",
  "org.springframework" % "spring-expression" % "3.2.1.RELEASE",
  "org.springframework" % "spring-aop" % "3.2.1.RELEASE",
  "org.springframework" % "spring-test" % "3.2.1.RELEASE" % "test",
  "org.hibernate" % "hibernate-entitymanager" % "4.1.9.Final",
  "cglib" % "cglib" % "2.2.2",
  "joda-time" % "joda-time-hibernate" % "1.3",
  "org.jadira.usertype" % "usertype.core" % "3.1.0.CR1",
  "org.hibernate" % "hibernate-core" % "3.6.8.Final",
  "org.hibernate" % "hibernate-annotations" % "3.5.6-Final",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "com.google.code.gson" % "gson" % "2.2.4",
  "com.feth" %% "play-authenticate" % "0.5.0-SNAPSHOT",
  "be.objectify" %% "deadbolt-java" % "2.2-RC4",
  "be.objectify" %% "deadbolt-scala" % "2.2-RC2"
)


play.Project.playJavaSettings
