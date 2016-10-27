package example.merchant.galaxy.logic

import scala.collection.mutable.HashMap

object IntergalacticTransactionProcessor {
  /* `=' regular expression used for token vs roman number mapping
   * '$' regular expression used for metal credits information provided lines
   * '?' regular expression used for matching How Many questions
   * '???' regular expression used for matching How Much questions
   */
  val errorMsg = "I have no idea what you are talking about".r
  val `=` = "^([a-z]+) is ([I|V|X|L|C|D|M])$".r
  val `$` = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$".r
  val `?` = "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$".r
  val `???` = "^how much is ((?:\\w+[^0-9] )+)\\?$".r

  /*repeatedTokenCount - the tokens which can be repeated up to three consecutive times
   * nonreatableTokenCount - Never repeated
   * tokenRomanValueMapper - token to roman value mapping {pish=X, tegj=L, prok=V, glob=I}
   * metalDecimalValueMapper - metal to per unit value mapping {Gold=14450.0, Iron=195.5, Silver=17.0}
   * transactionUnit - Unit used for transaction (Credits)
   */
 
  var repeatedTokenCount = HashMap[String, Int]("I" -> 0, "X" -> 0, "C" -> 0, "M" -> 0)
  var nonreatableTokenCount = HashMap[String, Int]("D" -> 0, "L" -> 0, "V" -> 0)
  val tokenRomanValueMapper = new HashMap[String, String]()
  val metalDecimalValueMapper = new HashMap[String, Double]()
  var transactionUnit = ""

  object RomanNumber extends Enumeration {
    def apply(romanValue: String) = {
      romanValue match {
        case "I" => DecimalValue("I", 1)
        case "V" => DecimalValue("V", 5)
        case "X" => DecimalValue("X", 10)
        case "L" => DecimalValue("L", 50)
        case "C" => DecimalValue("C", 100)
        case "D" => DecimalValue("D", 500)
        case "M" => DecimalValue("M", 1000)
      }

    }
    protected case class DecimalValue(roman: String, decimalValue: Int) extends super.Val()
    implicit def convert(value: Value) = value.asInstanceOf[Value]
  }

  def apply1(filePath: String) = new IntergalacticTransactionProcessor(filePath)
}
class IntergalacticTransactionProcessor(override val filePath: String) extends TokenValueProcessor
  with UnitsValueProcessor with QuestionProcessor with InputParser {

}