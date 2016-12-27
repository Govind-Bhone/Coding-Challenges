package example.merchant.galaxy.main

import example.merchant.galaxy.logic.IntergalacticTransactionProcessor

/*
 * this is the startup file for the Application
 * it accept the filePath as program argument from the console 
 * if not provided explicitly then 
 * default file get processed by the program (src/main/resources/input.txt)
 */

object Main extends App {
  val filePath = if (args.length != 0) Some(args(0)) else None
  val processor = IntergalacticTransactionProcessor(filePath)
  processor.processFile(filePath)
}