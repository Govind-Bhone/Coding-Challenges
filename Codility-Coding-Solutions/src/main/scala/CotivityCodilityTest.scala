object CotivityTest extends App {
  def solution(s: String, t: String): Boolean = {
    // write your code in Scala 2.11
    val sb1 = new StringBuilder()
    val sb2 = new StringBuilder()
    for (s1 <- s) {
      if (s1.isDigit) {
        val digit = s1.toInt - 49
        (0 to digit).foreach(f => sb1.++=("?"))
      } else
        sb1.+=(s1)
    }

    for (s2 <- t) {
      if (s2.isDigit) {
        val digit = s2.toInt - 49
        (0 to digit).foreach(f => sb2.++=("?"))
      } else
        sb2.+=(s2)
    }

    val decodedStr1 = sb1.result()
    val decodedStr2 = sb2.result()

    var bool = false
    if (decodedStr1.length != decodedStr2.length) {
      bool = false
    }
    else {
      scala.util.control.Breaks.breakable{
        for (i <- 0 to decodedStr1.length - 1) {
          val str1 = decodedStr1.charAt(i).toString
          val str2 = decodedStr2.charAt(i).toString
          if (!str1.equals("?") && !str2.equals("?")) {
            bool = str1.equals(str2)
            scala.util.control.Breaks.break()
          }else {
            bool=true
          }
        }
      }
    }
    bool
  }

  println(solution("ba1", "1Ad")) // ba? ?Ad
  println(solution("A2Le","2pL1"))
  println(solution("a10","10a")) //
  println(solution("3x2x","8"))

  println(solution("10a","a10"))
  println(solution("3a4b3c","4a3c5b"))
  println(solution("aaaa4bbbb","xxxx4yyyy"))
  println(solution("1an1n1","a11n1n"))

  println(solution("abc","a"))
  println(solution("a","anc"))

  def solution(s: String): String = {
    val str = s.replace("-", "").replace(" ", "")
    if (str.length % 3 == 1) {
      val (str1, str2) = str.splitAt(str.length - 4)
      str1.grouped(3).mkString("-").concat("-" + str2.grouped(2).mkString("-"))
    } else {
      str.grouped(3).mkString("-")
    }
  }

  println(solution("00-44  48 5555 8361"))

  println(solution("0 - 22 1985--324"))

  println(solution("023-456-78-99"))
  println(solution("023-456-789-11"))
  println(solution("023-456-789-819"))
  println(solution("456 45 67 78 99"))
  println(solution("2 3 4 5 6 7 8 9 "))
  println(solution("234-- --- 56 --89 "))
}
