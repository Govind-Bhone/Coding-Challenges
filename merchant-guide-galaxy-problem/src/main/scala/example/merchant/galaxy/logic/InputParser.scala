package example.merchant.galaxy.logic

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.FileReader
import java.io.IOException
import scala.io.Source
import scala.util.{Try, Success, Failure}
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import scala.io.BufferedSource

trait InputParser extends Op {

  import IntergalacticTransactionProcessor._

  def filePath: Option[String]

  /* this method check for the filePath if it is null then use the default file for creating
   * inputstream else get the path of the provided file to create inputstream
   */
  def inputstream: Try[java.io.InputStream] = if (filePath == None) Try(this.getClass().getClassLoader().getResourceAsStream("input.txt"))
  else
    Try(Files.newInputStream(Paths.get(filePath.get), StandardOpenOption.READ))

  /* read the all lines from file at a time */
  def readLines(source: BufferedSource) = source.getLines()

  /*  this function use to read the file and pass the line by line input to 
   *  processLine function 
   */
  def processFile(filePath: Option[String]) = {
    var source: BufferedSource = null
    inputstream match {
      case Success(in) =>
        source = Source.fromInputStream(in)
        readLines(source).foreach(processLine)
        source.close()
      case Failure(e) => println("[InputParser]-File not Found")
    }
  }

  /* process each line with validation 
   * calls the checkFlag method to get the type of line 
   * then using pattern matching we are calling different processing
   * logic
   */
  def processLine(line: String) = {
    checkFlag(line) match {
      case Assignment => processTokenRomanValueAssignment(line)
      case Credits => processNonCurrencyItems(line)
      case HowMany =>
        val (tokens, total) = processHowManyQuestion(line)
        if (total != 0)
          println(s"${tokens} is ${total} ${transactionUnit}")

      case HowMuch =>
        val (tokens, tokenDecimalValue) = processHowMuchQuestion(line)
        if (tokenDecimalValue != 0)
          println(s"${tokens} is ${tokenDecimalValue}")
      case InvalidInput => println(s"${errorMsg}")
    }
  }

  /* check the line against each regular expression to match */
  def checkFlag(line: String): Input = {
    line match {
      case `=`(token, roman) => Assignment
      case `$`(tokens, metal, value, unit) => Credits
      case `?`(unit, tokens, metal) => HowMany
      case `???`(tokens) => HowMuch
      case _ => InvalidInput
    }
  }
}