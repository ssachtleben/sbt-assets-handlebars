package com.ssachtleben.sbt.handlebars

import com.typesafe.sbt.jse.SbtJsTask
import sbt._
import com.typesafe.sbt.web.SbtWeb
import spray.json.{JsBoolean, JsObject}
import sbt.Keys._

object Import {

  object HandlebarsKeys {
    val handlebars = TaskKey[Seq[File]]("handlebars", "Invoke the Handlebars compiler.")

    val bare = SettingKey[Boolean]("handlebars-bare", "Compiles JavaScript that isn't wrapped in a function.")
    val sourceMap = SettingKey[Boolean]("handlebars-source-map", "Outputs a v3 sourcemap.")
  }

}

object HandlebarsPlugin extends AutoPlugin {

  override def requires = SbtJsTask

  override def trigger = AllRequirements

  val autoImport = Import

  import SbtWeb.autoImport._
  import WebKeys._
  import SbtJsTask.autoImport.JsTaskKeys._
  import autoImport.HandlebarsKeys._

  val handlebarsUnscopedSettings = Seq(
    includeFilter := "*.tmpl",
    jsOptions := JsObject(
      "bare" -> JsBoolean(bare.value),
      "sourceMap" -> JsBoolean(sourceMap.value)
    ).toString()
  )

  override def projectSettings = Seq(
    bare := false,
    sourceMap := true

  ) ++ inTask(handlebars)(
    SbtJsTask.jsTaskSpecificUnscopedSettings ++
      inConfig(Assets)(handlebarsUnscopedSettings) ++
      inConfig(TestAssets)(handlebarsUnscopedSettings) ++
      Seq(
        moduleName := "handlebars",
        shellFile := getClass.getClassLoader.getResource("handlebars.js"),

        taskMessage in Assets := "Handlebars compiling",
        taskMessage in TestAssets := "Handlebars test compiling"
      )
  ) ++ SbtJsTask.addJsSourceFileTasks(handlebars) ++ Seq(
    handlebars in Assets := (handlebars in Assets).dependsOn(webModules in Assets).value,
    handlebars in TestAssets := (handlebars in TestAssets).dependsOn(webModules in TestAssets).value
  )

}