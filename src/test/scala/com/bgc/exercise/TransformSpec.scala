package com.bgc.exercise

import com.github.mrpowers.spark.fast.tests.DataFrameComparer
import org.scalatest.{Matchers, WordSpec}

class TransformSpec
  extends WordSpec
    with Matchers
    with SparkSessionSpecWrapper
    with DataFrameComparer {


  import spark.implicits._

  "Transform" should {
    "rank movies correctly" in {
      //Given
      val movieRatings = Seq(
        ("tt0000001", 5.6d, 1550),
        ("tt0000002", 6.1d, 187),
        ("tt0000003", 6.5d, 1207),
        ("tt0000004", 6.2d, 114)
      ).toDF("tconst", "averageRating", "numVotes")

      // When
      val rankedMovies = Transform.rankMovies(spark, movieRatings).select("tconst", "averageRating", "numVotes")

      // Then
      val expectedRanking = Seq(
        ("tt0000001", 5.6d, 1550),
        ("tt0000003", 6.5d, 1207),
        ("tt0000002", 6.1d, 187),
        ("tt0000004", 6.2d, 114)
      ).toDF("tconst", "averageRating", "numVotes")

      assertSmallDatasetEquality(rankedMovies, expectedRanking)
    }

    "filter movies with less than 50 votes" in {
      //Given
      val movieRatings = Seq(
        ("tt0000001", 5.6d, 1550),
        ("tt0000002", 6.1d, 49),
        ("tt0000003", 6.5d, 1207),
        ("tt0000004", 6.2d, 114)
      ).toDF("tconst", "averageRating", "numVotes")

      // When
      val rankedMovies = Transform.rankMovies(spark, movieRatings).select("tconst", "averageRating", "numVotes")

      // Then
      val expectedRanking = Seq(
        ("tt0000001", 5.6d, 1550),
        ("tt0000003", 6.5d, 1207),
        ("tt0000004", 6.2d, 114)
      ).toDF("tconst", "averageRating", "numVotes")

      assertSmallDatasetEquality(rankedMovies, expectedRanking)
    }
  }

}
