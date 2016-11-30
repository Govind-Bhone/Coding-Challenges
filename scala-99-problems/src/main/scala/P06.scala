/**
  * Created by govind.bhone on 11/24/2016.
  */
object P06 extends App {

  // very efficient solution O(logn)
  def isPalindrom[T](xs: List[T]): Boolean = {
    val length = xs.length

    for (i <- 0 to length / 2) {
      if (xs(i) != xs(length-1 - i)) {
        return false
      }
    }
    true
  }

  // complexity O(n * n)
  def isPalindrom2[T](list: List[T]): Boolean = {
    list match {
      case Nil => true
      case _ :: Nil => true
      case _ if list.head == list.last => isPalindrom2(list.tail.init)
      case _ => false
    }
  }


  def isPalindrom1[T](xs:List[T]):Boolean=xs.reverse==xs

  assert(isPalindrom(List(1,2,3,2,1))==true)
  assert(isPalindrom2(List(1,2,3,2,1))==true)
  assert(isPalindrom1(List(1,2,3,2,1))==true)
}
