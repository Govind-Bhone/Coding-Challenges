object Task2 extends App {

  def solution(s: String): String = {
    val lPhoneNumber = s.replaceAll("[^0-9]", "").toCharArray()
    val lFormatedPhoneNum = new scala.collection.mutable.StringBuilder
    for (i <- 1 to lPhoneNumber.length) {
      lFormatedPhoneNum.append(lPhoneNumber(i - 1))
      if ((i % 3 == 0)) {
        lFormatedPhoneNum.append("-")
      }
    }
    // Corner case to deal with when - in end
    if (lFormatedPhoneNum.lastIndexOf("-") == lFormatedPhoneNum.length - 1) {
      return lFormatedPhoneNum.substring(0, lFormatedPhoneNum.length - 1)
    }
    // Corner case to deal with when only single digit is present after -
    else if ((lFormatedPhoneNum.length > 1) && lFormatedPhoneNum.lastIndexOf("-") == lFormatedPhoneNum.length - 2) {
      return lFormatedPhoneNum.substring(0, lFormatedPhoneNum.length - 3) + "-" + lFormatedPhoneNum.charAt(lFormatedPhoneNum.length - 3) + lFormatedPhoneNum.charAt(lFormatedPhoneNum.length - 1)
    }
    return lFormatedPhoneNum.toString()
  }

  println(solution("00-44  48 5555 8361"))
  println(solution("0 - 22 1985--324"))
  println(solution("023-456-78-99"))
  println(solution("023-456-789-11"))
  println(solution("023-456-789-819"))
  println(solution("456 45 67 78 99"))
  println(solution("2 3 4 5 6 7 8 9 "))
  println(solution("234-- --- 56 --89 "))
  println(solution("- 34567--56-89--"))
  println(solution("234-1"))
  println(solution("22"))
}
