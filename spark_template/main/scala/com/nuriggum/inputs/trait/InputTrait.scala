package com.nuriggum.inputs.`trait`

import org.apache.spark.sql.DataFrame

trait InputTrait {

  val options : Map[String,String]
  def createStructuredStreaming(): DataFrame


}
