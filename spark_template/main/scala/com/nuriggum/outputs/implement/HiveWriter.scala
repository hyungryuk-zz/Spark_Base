package com.nuriggum.outputs.implement

import com.nuriggum.outputs.`trait`.WriteTrait
import org.apache.spark.sql.DataFrame

/**
  *
  * @param _options
  */
class HiveWriter(_options:Map[String,String]) extends WriteTrait {
  override val options: Map[String, String] = _options

  override def createStructuredStreaming(df: DataFrame): Unit = ???
}
