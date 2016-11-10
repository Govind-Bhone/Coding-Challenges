/**
  * Created by govind.bhone on 11/10/2016.
  */
object Solution7 {

  def main(args: Array[String]) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
    val T = readInt
    for (t <- 0 until T) {
      val str = readLine.toCharArray
      val sb1 = new scala.collection.mutable.StringBuilder
      val sb2 = new scala.collection.mutable.StringBuilder

      for (i <- 0 to str.length - 1) {
        if (i % 2 == 0) sb1.+=(str(i)) else sb2.+=(str(i))
      }
      println(sb1.result + " " + sb2.result)
    }
  }
}
