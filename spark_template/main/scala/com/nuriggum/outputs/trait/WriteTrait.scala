package com.nuriggum.outputs.`trait`

import org.apache.spark.sql.DataFrame

trait WriteTrait {

  val options : Map[String,String]
  def createStructuredStreaming(df : DataFrame): Unit
}
