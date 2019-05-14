package com.nuriggum.inputs.implement.factory

import com.nuriggum.inputs.`trait`.InputTrait
import com.nuriggum.inputs.implement.{FileInput, KafkaInput, SocketInput}

object InputFactory {

  def createInput(inputType: String, options:Map[String,String]): InputTrait ={
    inputType match{
      case "kafka"=>return new KafkaInput(options)
      case "file"=>return new FileInput(options)
      case "socket"=>return new SocketInput(options)
    }
  }

}
