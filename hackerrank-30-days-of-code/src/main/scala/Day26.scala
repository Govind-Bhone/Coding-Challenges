/**
  * Created by govind.bhone on 11/23/2016.
  */
object Day26 {

  def main(args: Array[String]) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution*/
    val dateA = readLine.split(" ")
    val dA = dateA(0).toInt
    val mA = dateA(1).toInt
    val yA = dateA(2).toInt

    val dateE = readLine.split(" ")
    val dE = dateE(0).toInt
    val mE = dateE(1).toInt
    val yE = dateE(2).toInt


    val fine = if (yA > yE) {
      10000
    } else if (yA < yE) {
      0

    } else {
      if (mA > mE) {
        (mA - mE) * 500
      } else if (mA < mE) {
        0
      } else {
        if (dA > dE) {
          (dA - dE) * 15
        } else {
          0
        }
      }
    }
    println(fine)
  }
}


