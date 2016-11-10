/**
  * Created by govind.bhone on 11/10/2016.
  */
object Solution3 {
  def main(args: Array[String]) {
    val mealCost = readDouble
    val tipPer = readInt
    val taxPer = readInt

    val totalCost = math.round(mealCost + (mealCost * tipPer / 100) + (mealCost * taxPer / 100))
    println(s"The total meal cost is ${totalCost} dollars.")
  }
}
