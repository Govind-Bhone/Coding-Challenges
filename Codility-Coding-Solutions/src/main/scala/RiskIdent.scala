package riskident.coding.challenge

/**
  * Created by govind.bhone on 4/9/2017.
  */
class MinAvgWaitingTimeCalculator() {

  def process(customers: Array[Customer]):Long={
    val waitings = new CustomerMeanHeap()(scala.math.Ordering.Long)
    var currentTime = 0L
    var totalWaitingTime = 0L
    var index = 0
    while (!waitings.isEmpty || index < customers.length) {
      while (index < customers.length
        && customers(index).orderTime <= currentTime) {
        waitings.add(customers(index))
        index+=1
      }
      scala.util.control.Breaks.breakable{
        if (waitings.isEmpty ){
          currentTime = customers(index).orderTime
          scala.util.control.Breaks.break()
        }
        val served = waitings.pop
        currentTime += served.get.cookTime
        totalWaitingTime += currentTime - served.get.orderTime
      }

    }
    totalWaitingTime / customers.length
  }
}

object MinAvgWaitingTimeCalculator{
  def apply() = new MinAvgWaitingTimeCalculator()
}
