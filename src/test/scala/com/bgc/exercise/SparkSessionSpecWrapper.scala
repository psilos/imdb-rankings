package com.bgc.exercise

import org.apache.spark.sql.SparkSession

trait SparkSessionSpecWrapper {
  lazy val spark: SparkSession = {
    SparkSession
      .builder()
      .master("local")
      .appName("spark session")
      .config("spark.sql.shuffle.partitions", "1")
      .getOrCreate()
  }
}
