/**
  * Created by govind.bhone on 11/16/2016.
  */

object BinarySearchTest extends App {

  /*=======================Recursive Approach================================*/
  def binarySearch(xs: Array[Int], key: Int): Int = {
    def loop(low: Int, high: Int): Int = {
      val mid = (low + high) / 2
      if (xs(mid) < key) loop(mid + 1, high)
      else if (xs(mid) > key) loop(low, mid - 1)
      else mid
    }
    loop(0, xs.length - 1)
  }

  println(binarySearch(Array(1, 2, 3, 4, 5), 5))

  /*=========================Iterative approach=============================*/
  def binarySearch2(xs: Array[Int], key: Int): Int = {
    var low = 0
    var high = xs.length - 1

    while (low <= high) {
      var mid = (low + high) / 2
      if (xs(mid) < key) {
        low = mid + 1
      } else if (xs(mid) > key) {
        high = mid - 1
      } else {
        return mid
      }

    }
    return -1
  }

  println(binarySearch2(Array(1, 2, 3, 4, 5), 1))
  println(binarySearch2(Array(1, 2, 3, 4, 5), 10))
}
