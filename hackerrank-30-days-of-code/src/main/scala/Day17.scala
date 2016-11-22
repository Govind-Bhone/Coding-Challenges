/**
  * Created by govind.bhone on 11/22/2016.
  */
//Write your code here

import scala.util.Try
import scala.util.Success
import scala.util.Failure

class Calculator {
  def power(n: Int, p: Int): Int = {
    (n >= 0, p >= 0) match {
      case (true, true) => math.pow(n, p).toInt
      case _ => throw new Exception("n and p should be non-negative")
    }
  }
}

object Solution {

  def main(args: Array[String]) {
    var myCalculator = new Calculator();
    var T = readLine().toInt

    while (T > 0) {
      val Array(n, p) = readLine().split(" ").map(_.toInt);
      try {
        var ans = myCalculator.power(n, p);
        println(ans);
      }
      catch {
        case e: Exception => {
          println(e.getMessage());
        }
      }
      T -= 1;
    }
  }
}