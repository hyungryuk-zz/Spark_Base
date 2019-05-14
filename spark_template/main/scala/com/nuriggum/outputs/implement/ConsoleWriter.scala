package com.nuriggum.outputs.implement

import com.nuriggum.outputs.`trait`.WriteTrait
import org.apache.spark.sql.DataFrame

/**
  *
  * @param _options
  */
class ConsoleWriter(_options:Map[String,String]) extends WriteTrait{
  override val options: Map[String, String] = _options

  override def createStructuredStreaming(df: DataFrame): Unit = {
    val query = df.writeStream
      .outputMode("append")
      .format("console")
      .start()

    query.awaitTermination()
  }
}
