package example.merchant.galaxy.main

import example.merchant.galaxy.logic.IntergalacticTransactionProcessor
/*
 * this is the startup file for the Application
 * it accept the filePath as program argument from the console 
 * if not provided explicitly then 
 * default file get processed by the program (src/main/resources/input.txt)
 */

object Main extends App {
  var filePath: String = null
  if (args.length != 0)
    filePath = args(0)
  val processor = IntergalacticTransactionProcessor.apply1(filePath)
  processor.processFile(filePath)
}