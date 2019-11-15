package com.bgc.exercise

import pureconfig._
import pureconfig.generic.auto.exportReader

import scala.util.Try

case class Conf(inputPath: String)

object Conf {
  def parse: Try[Conf] = Try(loadConfigOrThrow[Conf])
}
