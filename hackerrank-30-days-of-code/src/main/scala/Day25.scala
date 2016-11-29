/**
  * Created by govind.bhone on 11/23/2016.
  */
object Day25 {


  def isPrime(n: Int) = {
    if (n == 1) "Not prime"
    if ((2 to n - 1).filter(f => f * f <= n).dropWhile(t => n % t != 0).length > 0) "Not prime" else "Prime"
  }

  def isPrimePerformantAndConcise(n: Int): String = if (n >= 2 && (2 to math.sqrt(n).toInt).forall(n % _ != 0))
    "Prime"
  else "Not prime"


  def isPrimeTimeEfficient(n: Int): String = {
    if (n == 1) return "Not prime"
    var result = "Prime"
    var i = 2
    while (i * i <= n) {
      if (n % i == 0) result = "Not prime"
      i = i + 1
    }
    result
  }

  def main(args: Array[String]) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
    val T = readInt()
    for (i <- 0 to T) {
      println(isPrimePerformantAndConcise(readInt))
    }
  }
}