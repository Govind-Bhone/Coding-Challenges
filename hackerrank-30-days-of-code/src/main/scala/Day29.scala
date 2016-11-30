/**
  * Created by govind.bhone on 11/24/2016.
  */
object Day29 {
//
//  When k is ODD , k-1 is EVEN , k-1 can always be reached by (k-1) & k.
//    In binary form:
//    k   = 11
//  k-1 = 10
//  k-1 == (k-1) & k
//  That is , ((k-1) | k) is always k. And ((k-1) | k) <= n is always TRUE.
//  When k is EVEN , k-1 is ODD , k-1 can only be reached if and only if ((k-1) | k) <= n
//  is TRUE
//
//  Why?
//    In binary form:
//    k   = 10110
//  k-1 = 10101
//  pos = 10111
//  k-1 == (k-1) & pos
//  You can get k-1 if pos <= n is TRUE. And you can get pos by ((k-1) | (k-1+1)) ,
//  that is , ((k-1) | k). Otherwise , you just need to follow the process
//  above when k is ODD (because k-1 is ODD) , then you get the answer k-2.
  // solution which is not efficient

  def maximumPossibleValue(n: Int, k: Int) = (1 to n).combinations(2).map(f => f(0) & f(1)).filter(_ < k).toList.sorted.last

  //efficient solution without calculating all permutation
  def maximumPossibleValue2(n: Int, k: Int) =
    //if (((k - 1) | k) > n && k % 2 == 0) k - 2 else k - 1
    if(((k-1)|k)<=n)k-1 else k-2

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

