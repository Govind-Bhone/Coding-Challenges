/**
  * Created by govind.bhone on 11/20/2016.
  */
object P01 extends App {

  def last[T](xs: List[T]): T = {
    xs match {
      case Nil => throw new NoSuchElementException("List is empty")
      case h :: Nil => h
      case _ :: tail => last(tail)
    }
  }

  def last1[T](xs: List[T]): T = xs.last

  def last2(xs: List[Int]): Int = {
    val current = xs.iterator
    var last = 0
    while (current.hasNext) {
      last = current.next()
    }
    last
  }

  def last3[T](xs: List[T]): T = xs.splitAt(xs.length - 1)._2.head

  def last4[T](xs: List[T]) = xs.reverse.head

  def last5[T](xs: List[T]) = xs.drop(xs.size - 1)

  def last6[T](xs: List[T]) = xs.slice(xs.length - 1, xs.length)

  def last7[T](xs: List[T]) = xs.takeRight(1)


  println(last(List(1, 2, 3, 4, 5)))
  println(last1(List(1, 2, 3, 4, 5)))
  println(last2(List(1, 2, 3, 4, 5)))
  println(last3(List(1, 2, 3, 4, 5)))
}
