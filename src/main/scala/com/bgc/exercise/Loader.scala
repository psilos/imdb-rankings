package com.bgc.exercise

import org.apache.spark.sql.{DataFrame, SparkSession}

object Loader {

  def loadData(sparkSession: SparkSession, config: Conf): DataFrame = {
    sparkSession
      .read
      .option("sep", "\t")
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(config.inputPath)
      .cache()
  }

}
