package com.ivanpfalcao.scala

import java.io.File
import com.typesafe.config.{ Config, ConfigFactory }


class ConfigurationFile(configFile: String) {

  val config = ConfigFactory.parseFile(new File(configFile))

  def getInputFilePath: String = {
    config.getString("inputPath")
  }

  def getOutputFilePath: String = {
    config.getString("outputPath")
  }

}
