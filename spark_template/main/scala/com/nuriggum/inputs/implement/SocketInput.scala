package com.nuriggum.inputs.implement

import com.nuriggum.inputs.`trait`.InputTrait
import org.apache.spark.sql.DataFrame

/**
  *
  * @param _options filePath
  */
class SocketInput(val _options:Map[String,String]) extends InputTrait{

  override val options: Map[String, String] = _options

  override def createStructuredStreaming(): DataFrame = ???
}
