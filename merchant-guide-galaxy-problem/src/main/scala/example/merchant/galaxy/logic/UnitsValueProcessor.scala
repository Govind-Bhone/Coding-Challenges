package example.merchant.galaxy.logic

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

trait UnitsValueProcessor extends Op {

  private val EMPTY_PLACEHOLDER = "EMPTY"
  private val REPEATED_ROMAN_COUNT_VALUE = 3
  private val NON_REPEATED_ROMAN_COUNT_VALUE = 1
  private val INITIAL_COUNT_VALUE = 0

  import IntergalacticTransactionProcessor._

  private def updateTransactionUnit(unit: String) = transactionUnit = unit

  private def reInitialize(): Unit = {
    repeatedTokenCount.foreach(e => repeatedTokenCount.put(e._1, INITIAL_COUNT_VALUE))
    nonreatableTokenCount.foreach(e => repeatedTokenCount.put(e._1, INITIAL_COUNT_VALUE))
  }

  /* update the metal per unit value */
  def processNonCurrencyItems(line: String) {
    val `$`(tokens, metal, value, unit) = line
    updateTransactionUnit(unit)
    metalDecimalValueMapper.put(metal, (value.toFloat) / getDecimalValue(tokens.split("""\s+""")))
  }

  /* used to calculate the decimal value of the token */
  def getDecimalValue(tokenarray: Array[String]): Int = {

    def validateTokenCountRules(tokenMap: HashMap[String, Int], index: Int, boundaryValue: Int): Unit = {
      val lastRomanValue = if (index != 0) tokenRomanValueMapper(tokenarray(index - 1)) else EMPTY_PLACEHOLDER
      val romanValue = tokenRomanValueMapper(tokenarray(index))
      if (!tokenRomanValueMapper.contains(tokenarray(index))) {
        throw InvalidToken("Invalid token detected!")
      }

      if (tokenMap.contains(romanValue)) {
        if (!lastRomanValue.equals(EMPTY_PLACEHOLDER) && !lastRomanValue.equals(romanValue))
          tokenMap.put(romanValue, INITIAL_COUNT_VALUE)
        tokenMap.put(romanValue, tokenMap(romanValue) + 1)
        if (tokenMap(romanValue) > boundaryValue) {
          throw RomanDigitRepeatedError(s"ERROR-${romanValue} Roman Digit Never be repeated more than ${boundaryValue} times")
        }
      }
    }

    val romans = for {
      index <- 0 until tokenarray.length
    } yield {
      validateTokenCountRules(repeatedTokenCount, index, REPEATED_ROMAN_COUNT_VALUE)
      validateTokenCountRules(nonreatableTokenCount, index, NON_REPEATED_ROMAN_COUNT_VALUE)
      RomanNumber(tokenRomanValueMapper(tokenarray(index))).decimalValue
    }
    reInitialize()
    doSubstract(romans.toArray).reduce(_ + _)
  }

  private def doSubstract(numbers: Array[Int]) = {
    for (i <- 0 until numbers.length - 1) {
      if (numbers(i) < numbers(i + 1)) {
        numbers(i) = -numbers(i)
      }
    }
    numbers
  }
}