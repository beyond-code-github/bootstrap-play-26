import play.sbt.PlayImport.filters
import sbt.Keys.{version, _}
import sbt._

import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {


  val appName = "bootstrap-play-25"

  val appDependencies = Dependencies.compile ++ Dependencies.test


  lazy val library = Project(appName, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      scalaVersion := "2.11.11",
      libraryDependencies ++= appDependencies,
      crossScalaVersions := Seq("2.11.11"),
      parallelExecution in Test := false,
      scalacOptions ++= Seq(
//        "-Xfatal-warnings",
        "-deprecation"
      ),
      resolvers := Seq(
        Resolver.bintrayRepo("hmrc", "releases"),
        Resolver.bintrayRepo("hmrc", "snapshots"),
        Resolver.bintrayRepo("hmrc", "release-candidates"),
        Resolver.typesafeRepo("releases"),
        Resolver.jcenterRepo
      )
  ).disablePlugins(sbt.plugins.JUnitXmlReportPlugin)

}

object Dependencies {

  import play.core.PlayVersion

  val compile = Seq(
    filters,
    "uk.gov.hmrc" %% "crypto" % "4.4.0",
    "uk.gov.hmrc" %% "http-verbs" % "7.1.0",
    "uk.gov.hmrc" %% "http-verbs-play-25" % "0.9.0",
    "uk.gov.hmrc" %% "play-auditing" % "3.2.0",
    "uk.gov.hmrc" %% "auth-client" % "0.2.0",
    "uk.gov.hmrc" %% "play-health" % "2.1.0",
    "uk.gov.hmrc" %% "play-graphite" % "3.2.0",
    "uk.gov.hmrc" %% "logback-json-logger" % "3.1.0",
    "com.typesafe.play" %% "play" % "2.5.12",
    "de.threedimensions" %% "metrics-play" % "2.5.13",
    "ch.qos.logback" % "logback-core" % "1.1.7"
  )

  val test = Seq(
    "com.typesafe.play" %% "play-test" % PlayVersion.current % "test",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "org.mockito" % "mockito-all" % "1.9.5" % "test",
    "org.pegdown" % "pegdown" % "1.5.0" % "test",
    "com.github.tomakehurst" % "wiremock" % "2.7.1" % "test",
    "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % "test",
    "uk.gov.hmrc" %% "hmrctest" % "2.3.0" % "test"
  )

}
