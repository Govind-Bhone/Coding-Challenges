import scala.util.Random

/**
  * Created by govind.bhone on 11/27/2016.
  */
object Day27 extends App {

  val T = Random.nextInt(4)+1
  val noOfStudent = Random.nextInt()

  def printInput(input: Array[Int]): Unit = {
    println(input.mkString(" "))
  }

  printInput(Array(T))
  for (i <- 1 to T) {
    val noOfStudent = Random.nextInt(4)+1
    printInput(Array(noOfStudent, Random.nextInt(noOfStudent)))
    printInput( if(noOfStudent <= 3 )Array(-1,0,1) else Array(-1,0,1)++ Array.fill(noOfStudent-3) {
      Random.nextInt(65536) - 32768
    })
  }


}