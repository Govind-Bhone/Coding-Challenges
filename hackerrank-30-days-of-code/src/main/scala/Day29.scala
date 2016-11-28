/**
  * Created by govind.bhone on 11/24/2016.
  */
object Day29 {

  //solution which is not efficient
  def maximumPossibleValue(n: Int, k: Int) = (1 to n).combinations(2).map(f => f(0) & f(1)).filter(_ < k).toList.sorted.last

  //efficient solution without calculating all permutation
  def maximumPossibleValue2(n: Int, k: Int) = if (((k - 1) | k) > n && k % 2 == 0) k - 2 else k - 1

  def main(args: Array[String]) {
    val t = readInt

    for (_ <- 1 to t) {
      val nk = readLine.split(" ").map(x => x.toInt)
      val n = nk(0)
      val k = nk(1)
      println(maximumPossibleValue2(n, k))
    }
  }
}

