package com.bgc.exercise

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession

import scala.util.{Failure, Success}

object Main extends App with LazyLogging {

  Conf.parse match {
    case Success(config) =>
      val sparkSession = SparkSession
        .builder()
        .appName("bgc-movie-ratings")
        .getOrCreate()

      val data = Loader.loadData(sparkSession, config)
      val top20 = Transform.rankMovies(sparkSession, data).take(20)

      sparkSession.stop()
    case Failure(ex) => logger.error(s"Failed to parse config with error: $ex")
  }
}
