/***
  *  Copyright (C) Competition Labs Ltd, Inc - All Rights Reserved
  *  Unauthorized copying of this file, via any medium is strictly prohibited
  *  Proprietary and confidential
  *  Written by Competition Labs Ltd, 2019
 */

/*** When developing locally do not modify this file ***/
name := "competitionlabs-transformers"

organization := "com.competitionlabs"

version := "1.15"

isSnapshot := true

scalaVersion := "2.12.8"

resolvers += Resolver.mavenLocal

val json4sVersion = "3.6.7"

libraryDependencies += "org.json4s" %% "json4s-ext" % json4sVersion
libraryDependencies += "org.json4s" %% "json4s-native" % json4sVersion
libraryDependencies += "org.json4s" %% "json4s-jackson" % json4sVersion
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0-M2" % Test
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += "org.apache.avro" % "avro" % "1.9.0"

