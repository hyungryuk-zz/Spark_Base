package com.nuriggum.outputs.implement.factory

import com.nuriggum.outputs.`trait`.WriteTrait
import com.nuriggum.outputs.implement.{ConsoleWriter, HBaseWriter, HiveWriter, KafkaWriter}

object OutputFactory {

  def createOutput(inputType: String, options:Map[String,String]): WriteTrait ={
    inputType match{
      case "console"=>return new ConsoleWriter(options)
      case "hbase"=>return new HBaseWriter(options)
      case "hive"=>return new HiveWriter(options)
      case "kafka"=>return new KafkaWriter(options)
    }
  }
}
