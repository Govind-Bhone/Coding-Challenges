/**
  * Created by govind.bhone on 11/16/2016.
  */
object ArrayTest extends App {

  def printArray(array: Array[Int]): Unit = {
    println((for {
      i <- 0 to array.length - 1
    } yield array(i)).mkString(","))
  }

  def max(array: Array[Int]): Int = {
    var max = Int.MinValue
    for (ele <- array) {
      if (ele > max) {
        max = ele
      }
    }
    max
  }

  def avg(array: Array[Int]): Double = {
    var sum = 0
    for (i <- 0 to array.length - 1) {
      sum += array(i)
    }
    sum / array.length
  }

  def copy(array: Array[Int]): Array[Int] = {
    val newArray = new Array[Int](array.length)
    for (ele <- array) {
      newArray(array.indexOf(ele)) = ele
    }
    newArray
  }

  def reverse(array: Array[Int]): Array[Int] = {
    for (i <- 0 until array.length / 2) {
      val tmp = array(array.length - 1 - i)
      array(array.length - 1 - i) = array(i)
      array(i) = tmp
    }
    array
  }


  def matrixMultiplication(array1: Array[Array[Int]], array2: Array[Array[Int]]): Array[Array[Int]] = {
    val N = array1.length
    val cArray = Array.fill(N, N)(0)
    //cArray.foreach(f=>println(f.toList))
    for (i <- 0 to N - 1) {
      for (j <- 0 to N - 1) {
        //cArray(i)(j)=0
        for (k <- 0 to N - 1) {
          cArray(i)(j) += aArray(i)(k) + bArray(k)(j)
        }
      }
    }
    cArray
  }

  val aArray = Array(
    Array(1, 2, 3),
    Array(4, 5, 6),
    Array(7, 8, 9))
  val bArray = Array(
    Array(1, 2, 3),
    Array(4, 5, 6),
    Array(7, 8, 9))

  val array = Array[Int](1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  printArray(array)
  println(max(array))
  println(avg(array))
  println(copy(array).mkString(","))
  println(reverse(array).mkString(","))
  matrixMultiplication(aArray, bArray).foreach(f => println(f.mkString(",")))
}
