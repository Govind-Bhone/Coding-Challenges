package example.codility

/**
  * Created by govind.bhone on 1/31/2017.
  */
object FastFishCodilityTest extends App {
  def solution(A: Array[Int]): Int = {
    var n: Int = A.length;
    var result: Int = 0;
    var i: Int = 0;
    while (i < n - 1) {
      if (A(i) == A(i + 1))
        result = result + 1;
      i = i + 1;
    }
    var r: Int = 0;
    i = 0;
    while (i < n) {
      var count: Int = 0;
      if (i > 0) {
        if ((A(i - 1) != A(i)))
          count = count + 1;
        else
          count = count - 1;
      }
      if (i < n - 1) {
        if (A(i + 1) != A(i))
          count = count + 1;
        else
          count = count - 1;
      }
      r = Math.max(r, count);
      i = i + 1;
    }

    return result + r
  }


  println(solution(Array(1, 0, 1)))

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
      scala.util.control.Breaks.breakable {
        for (i <- 0 to decodedStr1.length - 1) {
          val str1 = decodedStr1.charAt(i).toString
          val str2 = decodedStr2.charAt(i).toString
          if (!str1.equals("?") && !str2.equals("?")) {
            bool = str1.equals(str2)
            scala.util.control.Breaks.break()
          } else {
            bool = true
          }
        }
      }
    }
    bool
  }

  println(solution("ba1", "1Ad")) // ba? ?Ad
  println(solution("A2Le", "2pL1"))
  println(solution("a10", "10a")) //
  println(solution("3x2x", "8"))

  def solution(a: Int, b: Int): Int = {
    // write your code in Scala 2.11
    val res = for {
      number <- a to b
    } yield {
      if (math.sqrt(number).isValidInt) Some(number) else None
    }
    res.head.get
  }

  println(solution(4, 17))

}
