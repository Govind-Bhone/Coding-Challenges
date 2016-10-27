package example.merchant.galaxy.logic

import scala.util.Success

/* trait which defines operation performed on input
 * 
 *  1. ProcessFile => read the file and lines from the file 
 *  2. processTokenRomanValueAssignment => Maps the token with the Roman Value
 *  3. processNonCurrencyItems => will process the non currency items like gold ,iron etc
 *  4. processHowManyQuestion  => process the questions of type HOW MANY
 *  5. processHowMuchQuestion => process the questions of type HOW MUCH
 *  6. getDecimalValue   => it will returns the decimal value of the token
 * 
 *  
 */

trait Op {
  def processFile(filePath: String)
  def processTokenRomanValueAssignment(line: String)
  def processNonCurrencyItems(line: String)
  def processHowManyQuestion(line: String):(String,Double)
  def processHowMuchQuestion(line: String):(String,Double)
  def getDecimalValue(tokenarray: Array[String]): Int
}

/* input types control plane messages  */
sealed trait Input
case object Assignment extends Input
case object Credits extends Input
case object HowMuch extends Input
case object HowMany extends Input
case object InvalidInput extends Input

