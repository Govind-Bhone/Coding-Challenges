package example.merchant.galaxy.logic

import scala.util.Try

trait QuestionProcessor extends Op {

  import IntergalacticTransactionProcessor._

  /*method call to process HowMany Question */
  def processHowManyQuestion(line: String): (String, Double) = {
    val `?`(unit, tokens, metal) = line
    if (!unit.equals(IntergalacticTransactionProcessor.transactionUnit)) {
      throw InvalidTransactionUnit("Invalid Transaction Unit")
    }
    val tokenDecimalValue = getDecimalValue(tokens.split("""\s+"""))
    val value = if (metalDecimalValueMapper.contains(metal)) metalDecimalValueMapper(metal) else 0
    (tokens, value * tokenDecimalValue)
  }

  /*method call to process HowMuch Question */
  def processHowMuchQuestion(line: String): (String, Double) = {
    val `???`(tokens) = line
    val tokenDecimalValue = getDecimalValue(tokens.split("""\s+"""))
    (tokens, tokenDecimalValue)
  }
}