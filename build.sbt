name := """play-java-spring"""

version := "1.0-SNAPSHOT"

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
  "com.google.code.gson" % "gson" % "2.2.4"
)

play.Project.playJavaSettings
