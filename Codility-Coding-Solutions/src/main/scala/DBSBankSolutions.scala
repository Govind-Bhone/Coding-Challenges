package example.tictactoe.main

/**
  * Created by govind.bhone on 7/10/2017.
  */
object Solution2 extends App {

  //psychomatric
  def psychomatric(score: Array[Int], lowerLimit: Array[Int], upperLimit: Array[Int]) = {
    (for {
      i <- 0 until lowerLimit.length
    } yield {
      (0 until score.length).foldLeft(0)((acc, f) => if (score(f) >= lowerLimit(i) && score(f) <= upperLimit(i)) acc + 1 else acc)
    })
  }
  println("psychomatric : " + psychomatric(Array(6, 3, 5, 6, 8), Array(1, 2), Array(4, 6)).toList)
  println("psychomatric : " + psychomatric(Array(1, 3, 5, 6, 8), Array(2), Array(6)).toList)
  println("psychomatric : " + psychomatric(Array(4,8,7), Array(2, 4), Array(8, 4)).toList)
  println("psychomatric : " + psychomatric(Array(1,1), Array(1), Array(1)).toList)

  //consecutive sum
  def consecutiveSum(n: Int): Int = {
    var totalSum = 0

    for (i <- 1 until n) {
      var sum = 0
      scala.util.control.Breaks.breakable {
        for (j <- i until n) {
          sum += j
          if (sum == n) {
            totalSum += 1
            scala.util.control.Breaks.break()
          }
        }
      }
    }
    totalSum
  }

  println("consecutiveSum : " + consecutiveSum(15))


  //largest subset sum
  def largestSubSetSum(input: Array[Int]): Array[Int] = {
    def getAllFactors(a: Int) = {
      val list = new scala.collection.mutable.ListBuffer[Int].+=(1)
      for (i <- 2 to a) {
        if (a % i == 0) list.+=(i)
      }
      list.result()
    }

    val res = for {ele <- input} yield getAllFactors(ele).sum
    res
  }

  println("largestSubSetSum : " + largestSubSetSum(Array[Int](2, 4)).toList)
  println("largestSubSetSum : " + largestSubSetSum(Array[Int](10)).toList)
  println("largestSubSetSum : " + largestSubSetSum(Array[Int](5,6)).toList)

  //get total days
  def inFuture(capFirstPlayer: Int, capSecondPlayer: Int, prevWorkDoneFirstPlayer: Int): Int = {
    var day = -1

    var sumFirst = 0
    var sumSecond = 0

    if (capFirstPlayer >= capSecondPlayer) return day

    day = 1
    scala.util.control.Breaks.breakable {
      while (true) {
        sumSecond = day * capSecondPlayer
        sumFirst = day * capFirstPlayer
        if (sumSecond > (sumFirst + prevWorkDoneFirstPlayer)) {
          scala.util.control.Breaks.break()
        }
        day += 1
      }
    }
    return day
  }

  println("in Future  : " + inFuture(4, 5, 1))
  println("in Future  : " + inFuture(3,5,1))

  //get winner
  def getWinner(enrie: Array[Int], marie: Array[Int], types: String): String = {
    var start= 0
    val MARIE_WIN = "marie"
    val ENRIE_WIN = "enrie"
    val TIE = "tie"

    if (types.equals("odd"))
      start = 1

    val (marieToenrie,enrieToMarie)=(start until marie.length by 2).foldLeft((0,0))((acc,ele)=>
      (acc._1+marie(ele)-enrie(ele),acc._2+enrie(ele)-marie(ele)))

    if (marieToenrie > enrieToMarie)
      MARIE_WIN
    else if (marieToenrie < enrieToMarie)
      ENRIE_WIN
    else TIE
  }

  println("getWinner : " + getWinner(Array[Int](1, 2, 3, 4, 5), Array[Int](4, 3, 2, 1, 0), "even"))
  println("getWinner : " + getWinner(Array[Int](1, 2, 3, 4, 5), Array[Int](4, 3, 2, 1, 0), "odd"))
  println("getWinner : " + getWinner(Array[Int](9,9,9), Array[Int](-1,2,4), "even"))
  println("getWinner : " + getWinner(Array[Int](1,2,3), Array[Int](2,1,3), "even"))

  println("getWinner : " + getWinner(Array[Int](1,2,3), Array[Int](2,1,3), "even"))

  println("getWinner : " + getWinner(Array[Int](1,2,3), Array[Int](2,1,3), "odd"))
  println("getWinner : " + getWinner(Array[Int](1,1), Array[Int](1,1), "even"))

  //buy tickets
  def buyTickets(arr: Array[Int], index: Int):Int = {
    var time = 0
    var startIndex = 0

    while (arr(index) != 0) {
      if (arr(startIndex) != 0) {
        arr(startIndex) = arr(startIndex) - 1
        time += 1
      }
      if (startIndex == arr.length - 1)
        startIndex = 0
      else
        startIndex += 1
    }
    time
  }

  println("buyTickets : " + buyTickets(Array(2, 6, 3, 4, 5), 2))
  println("buyTickets : " + buyTickets(Array(5,5,2,3), 3))

}
