/**
  * Created by govind.bhone on 11/10/2016.
  */
object Solution10 {

  def factorial(n: Int): Int = {
    if (n == 0) 1
    else n * factorial(n - 1)
  }

  def main(args: Array[String]) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
    val inputNbr = readInt
    println(factorial(inputNbr))
  }
}
