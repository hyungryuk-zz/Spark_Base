package com.nuriggum.outputs.implement

import com.nuriggum.outputs.`trait`.WriteTrait
import org.apache.spark.sql.DataFrame

/**
  *
  * @param _options
  */
class KafkaWriter(_options:Map[String,String]) extends WriteTrait {
  override val options: Map[String, String] = _options

  override def createStructuredStreaming(df: DataFrame): Unit = {
    val query = df
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers", options.get("kafka.bootstrap.servers").get)
      .option("topic", options.get("topic").get)
      .start()

    query.awaitTermination()
  }
}
