package com.nuriggum

import java.io.FileInputStream
import java.util.Properties

import com.nuriggum.inputs.`trait`.InputTrait
import com.nuriggum.inputs.implement.factory.InputFactory
import com.nuriggum.outputs.`trait`.WriteTrait
import com.nuriggum.outputs.implement.factory.OutputFactory
import org.apache.spark.sql.SparkSession

object main {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Spark_Base")
      .enableHiveSupport()
      .getOrCreate()

    if(sys.env("RUNTIME_ENV").equals("LOCAL")){
      var prop = new Properties()
      prop.load(new FileInputStream("spark_template/main/resources/properties.properties"))
      prop.keySet().forEach(key=>{
        spark.conf.set(key.toString,prop.get(key.toString).toString)
      })
    }

    /*
    ==수집
     */

    spark.sparkContext.setLogLevel(spark.conf.get("log_level"))

    var options_for_input_kafka = Map[String,String](
      "kafka.bootstrap.servers"->spark.conf.get("kafka.bootstrap.server"),
      "subscribe"->spark.conf.get("kafka.topic")
    )

    var options_for_output_console = Map[String,String]()

    val inputSettings : InputTrait = InputFactory.createInput(spark.conf.get("input_type"),options_for_input_kafka)

    var dataFrame = inputSettings.createStructuredStreaming()


    /*
    ==변형
     */



    /*
    ==적재
    */
    var outputSettings : WriteTrait = OutputFactory.createOutput(spark.conf.get("output_type"),options_for_output_console)
    outputSettings.createStructuredStreaming(dataFrame.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)"))

  }
}
