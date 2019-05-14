package com.nuriggum.inputs.implement

import com.nuriggum.inputs.`trait`.InputTrait
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  *
  * @param _options filePath
  */
class KafkaInput(val _options:Map[String,String]) extends InputTrait{

  override val options: Map[String, String] = _options

  override def createStructuredStreaming(): DataFrame = {

    val spark = SparkSession.builder().getOrCreate()

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", options.get("kafka.bootstrap.servers").get)
      .option("subscribe", options.get("subscribe").get)
      .load()

    return df
  }



}
