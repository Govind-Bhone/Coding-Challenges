package example.merchant.galaxy.logic

import scala.util.Try

trait QuestionProcessor extends Op {
  import IntergalacticTransactionProcessor._
  
  /*method call to process HowMany Question */
  def processHowManyQuestion(line: String): (String, Double) = {
    val `?`(unit, tokens, metal) = line
    if (!unit.equals(IntergalacticTransactionProcessor.transactionUnit)) {
      println(errorMsg)
      System.exit(0)
    }
    val tokenDecimalValue = getDecimalValue(tokens.split("""\s+"""))
    var value = 0d
    if (metalDecimalValueMapper.contains(metal))
      value = metalDecimalValueMapper(metal)
    (tokens, value * tokenDecimalValue)
  }
  
   /*method call to process HowMuch Question */
  def processHowMuchQuestion(line: String): (String, Double) = {
    val `???`(tokens) = line
    val tokenDecimalValue = getDecimalValue(tokens.split("""\s+"""))
    (tokens, tokenDecimalValue)
  }
}