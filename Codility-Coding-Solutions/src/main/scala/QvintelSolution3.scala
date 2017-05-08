/**
  * Created by govind.bhone on 4/26/2017.
  * 
  */
object Solution3 extends App {

  def solution(a: Array[Int]): Int = {
    val length = a.length
    var s = 0
    var e = length - 1
    var max = 0
    var min = 0

    //scanning from left to right
    scala.util.control.Breaks.breakable {
      for (asLoop <- s to length - 2) {
        if (a(asLoop) > a(asLoop + 1)) {
          scala.util.control.Breaks.break()
        }
        s += 1
      }
    }

    //Array is already sorted
    if (s == length - 1) {
      return 0
    }

    // scanning from right to left
    scala.util.control.Breaks.breakable {
      for (desLoop <- e until 0 by -1) {
        if (a(desLoop) < a(desLoop - 1)) {
          scala.util.control.Breaks.break()
        }
        e -= 1
      }
    }

    // deciding min and max value
    max = a(s)
    min = a(s)
    for (i <- s + 1 to e) {
      if (a(i) > max)
        max = a(i)
      if (a(i) < min)
        min = a(i)
    }

    // Finding the lower bound
    scala.util.control.Breaks.breakable {
      for (lowerLoop <- 0 until s) {
        if (a(lowerLoop) > min) {
          s = lowerLoop
          scala.util.control.Breaks.break()
        }
      }
    }

    // Finding the upper bound
    scala.util.control.Breaks.breakable {
      for (upperLoop <- length - 1 to e + 1 by -1) {
        if (a(upperLoop) < max) {
          e = upperLoop
          scala.util.control.Breaks.break()
        }
      }
    }

    // calculating the length of array need to change positions
    e - s + 1
  }

  println(solution(Array(1, 2, 6, 5, 5, 8, 9)))
}
