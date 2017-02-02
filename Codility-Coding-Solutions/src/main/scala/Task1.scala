import scala.collection.mutable

object Task1 extends App {

  def solution(A: String, B: String): Int = {
    val cardValues = new mutable.HashMap[Char, Int]()
    cardValues.put('2', 2)
    cardValues.put('3', 3)
    cardValues.put('4', 4)
    cardValues.put('5', 5)
    cardValues.put('6', 6)
    cardValues.put('7', 7)
    cardValues.put('8', 8)
    cardValues.put('9', 9)
    cardValues.put('T', 10)
    cardValues.put('J', 11)
    cardValues.put('Q', 12)
    cardValues.put('K', 13)
    cardValues.put('A', 14)

    if (A == null || A.length() < 1 || A.length() > 1000) {
      return -1
    }

    if (B == null || B.length() < 1 || B.length() > 1000) {
      return -1
    }

    if (A.length() != B.length()) {
      return -1
    }

    var aliceCount = 0
    for (i <- 0 to A.length - 1) {
      scala.util.control.Breaks.breakable {
        val AChar = A.charAt(i)
        val BChar = B.charAt(i)

        val AInteger = cardValues(AChar)
        val BInteger = cardValues(BChar)

        if (AInteger == BInteger) scala.util.control.Breaks.break()

        if (AInteger > BInteger) aliceCount = aliceCount + 1
      }

    }
    return aliceCount
  }

  println(solution("23A84Q", "K2Q25J"))
}

