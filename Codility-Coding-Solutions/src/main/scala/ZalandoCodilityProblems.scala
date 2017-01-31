import scala.collection.mutable.ListBuffer


/*
Zalando codility test Interview Questions


 */
object ZalandoCodilityProblems extends App {
  
  /*
  *******************************Lift Problem*****************************************
  */
  def solution3(a: Array[Int], b: Array[Int], m: Int, x: Int, y: Int): Int = {
    // write your code in Scala 2.11
    var totalStops = 0
    var totalWeightPerRound = 0L
    var maxPersonsCount = 0
    var floorsToStop = scala.collection.mutable.ListBuffer[Int]()
    var currentPerson = 0
    var isPerRoundCapacityFull = false
    val queueSize = a.length

    while (currentPerson < queueSize) {
      processQueue()
    }

    def processQueue(): Unit = {
      if ((totalWeightPerRound + a(currentPerson)) <= y && (maxPersonsCount + 1) <= x) {
        totalWeightPerRound = totalWeightPerRound + a(currentPerson)
        maxPersonsCount += 1
        floorsToStop.+=(b(currentPerson))
        if (currentPerson == queueSize - 1)
          isPerRoundCapacityFull = true
        currentPerson += 1
      }
      else {
        isPerRoundCapacityFull = true
      }

      if (isPerRoundCapacityFull) {
        resetElevatorState()
      }
    }

    def resetElevatorState(): Unit = {
      totalStops += floorsToStop.distinct.size + 1
      floorsToStop = ListBuffer.empty[Int]
      maxPersonsCount = 0
      totalWeightPerRound = 0
      isPerRoundCapacityFull = false
    }

    return totalStops
  }
  
  /*
  ************************Zipping the two integer number digits *****************
  */

  def solution2(a: Int, b: Int): Int = {
    // write your code in Scala 2.11
    val sb = new scala.collection.mutable.StringBuilder()
    val aStr = a.toString.toList
    val bStr = b.toString.toList
    val zipped = aStr.zipAll(bStr, "-", "-")
    zipped.foreach(f => {
      sb.+=(f._1.toString.toCharArray.head);
      sb.+=(f._2.toString.toCharArray.head)
    })
    val result = sb.filter(_ != '-').result()
    if (result.toLong > 100000000L)
      -1
    else result.toInt
  }
  
  /*
  *****************Longest substring without Numbers **************************************
  */

  def solution1(s: String): Int = {
    var maxLen = Int.MinValue
    for (i <- 0 to s.length) {
      for (j <- i to s.length) {
        val subStr = s.substring(i, j)
        var isAlreadyContain = false
        var isDigit = false
        for (character <- subStr) {
          if (character.isDigit) {
            isDigit = true
          } else {
            if (!isAlreadyContain) {
              if (character.isUpper) {
                isAlreadyContain = true
              }
            }

          }
        }
        if (isAlreadyContain && !isDigit) {
          if (subStr.length > maxLen) maxLen = subStr.length
        }
      }
    }

    if (maxLen > 0)
      return maxLen
    else
      return -1
  }

  println(solution1("30Bb"))

  println(solution2(123, 67890))

  println(solution3(Array(40, 40, 100, 80, 20), Array(3, 3, 2, 2, 3), 5, 2, 200))
}
