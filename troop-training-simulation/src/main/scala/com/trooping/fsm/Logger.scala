package com.trooping.fsm

sealed trait LogLevel { def name: String }

case object INFO extends LogLevel { val name: String = "[INFO]" }
case object WARN extends LogLevel { val name: String = "[WARN]" }
case object ERROR extends LogLevel { val name: String = "[ERROR]" }

/*
 * Basic custom logger implementation 
 */
trait Logger {
  def log(loglevel: LogLevel, msg: String) {
    loglevel match {
      case INFO => scala.Console.println(s"${INFO.name} -${msg}")
      case WARN => scala.Console.println(s"${INFO.name} -${msg}")
      case ERROR => scala.Console.println(s"${INFO.name} -${msg}")
      case level @ _ => scala.Console.println(s"Invalid Level configuration for message ${msg}")
    }
  }
}