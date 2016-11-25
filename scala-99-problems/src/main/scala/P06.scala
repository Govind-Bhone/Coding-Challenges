/**
  * Created by govind.bhone on 11/24/2016.
  */
object P06 extends App {

  def isPalindrom[T](xs: List[T]): Boolean = {
    val length = xs.length

    for (i <- 0 to length / 2) {
      if (xs(i) != xs(length-1 - i)) {
        return false
      }
    }
    true
  }

  def isPalindrom1[T](xs:List[T]):Boolean=xs.reverse==xs

  println(isPalindrom(List(1,2,3,2,1)))
  println(isPalindrom1(List(1,2,3,2,1)))
}
