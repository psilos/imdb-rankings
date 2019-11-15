package com.bgc.exercise

import org.apache.spark.sql.{DataFrame, SparkSession}

object Transform {

  import org.apache.spark.sql.functions._

  def rankMovies(session: SparkSession, df: DataFrame): DataFrame = {
    import session.implicits._

    df
      // FIXME: Inneficient Window Operation
      .withColumn("averageNumberOfVotes", avg($"numVotes").over())
      .filter($"numVotes" geq 50)
      .sort((($"numVotes"/$"averageNumberOfVotes") * $"averageRating").desc)
  }
}
