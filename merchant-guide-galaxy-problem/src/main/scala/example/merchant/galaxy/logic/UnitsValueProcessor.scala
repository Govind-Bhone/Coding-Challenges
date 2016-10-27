package example.merchant.galaxy.logic

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

trait UnitsValueProcessor extends Op {
  import IntergalacticTransactionProcessor._

  /* update the metal per unit value */
  def processNonCurrencyItems(line: String) {
    val `$`(tokens, metal, value, unit) = line
    transactionUnit = unit
    metalDecimalValueMapper.put(metal, (value.toFloat) / getDecimalValue(tokens.split("""\s+""")))
  }

  /* used to calculate the decimal value of the token */
  def getDecimalValue(tokenarray: Array[String]): Int = {
    var value = 0
    val romans = ListBuffer[Int]()
    for (i <- 0 until tokenarray.length) {
      if (!tokenRomanValueMapper.contains(tokenarray(i))) {
        System.out.println("Invalid input detected!")
        System.exit(0)
      }
      val lastromanValue = if (i != 0) tokenRomanValueMapper(tokenarray(i - 1)) else "NULL"
      val romanValue = tokenRomanValueMapper(tokenarray(i))
      if (repeatedTokenCount.contains(romanValue)) {
        if (!lastromanValue.equals("NULL") && !lastromanValue.equals(romanValue))
          repeatedTokenCount.put(romanValue, 0)
        repeatedTokenCount.put(romanValue, repeatedTokenCount(romanValue) + 1)
        if (repeatedTokenCount(romanValue) > 3) {
          println(s"ERROR-${romanValue} Roman Digit Never be repeated more than 3 times")
          System.exit(0)
        }
      } else if (nonreatableTokenCount.contains(romanValue)) {
        if (!lastromanValue.equals("NULL") && !lastromanValue.equals(romanValue))
          nonreatableTokenCount.put(romanValue, 0)
        nonreatableTokenCount.put(romanValue, nonreatableTokenCount(romanValue) + 1)
        if (nonreatableTokenCount(romanValue) > 1) {
          println(s"ERROR-${romanValue} Roman Digit Never be repeated")
          System.exit(0)
        }
      } else {
        println("Invalid roman value detected")
      }
      romans.+=(RomanNumber(tokenRomanValueMapper(tokenarray(i))).decimalValue)
    }
    repeatedTokenCount = HashMap("I" -> 0, "X" -> 0, "C" -> 0, "M" -> 0)
    nonreatableTokenCount = HashMap("L" -> 0, "V" -> 0, "D" -> 0)
    doSubstract(romans).foreach(f => value += f)
    value
  }

  def doSubstract(numbers: ListBuffer[Int]) = {
    for (i <- 0 until numbers.length - 1) {
      if (numbers(i) < numbers(i + 1)) {
        numbers(i) = -numbers(i)
      }
    }
    numbers
  }
}