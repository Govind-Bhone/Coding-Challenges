package example.merchant.galaxy.logic

trait TokenValueProcessor extends Op {
  import IntergalacticTransactionProcessor._
  /* update token to roman value map */
  def processTokenRomanValueAssignment(line: String) {
    val `=`(token, roman) = line
    tokenRomanValueMapper.getOrElseUpdate(token, roman)
  }
}