/**
  * Created by govind.bhone on 11/24/2016.
  */
object P03 extends App {

  def findKthElement[T](k: Int, xs: List[T]) = {
    def find[T](i: Int, xs: List[T]): T = {
      xs match {
        case Nil if i <= k => throw new NoSuchElementException
        case h :: tail => if (i == k) h else find(i + 1, tail)
      }
    }
    if (k >= 0) find(0, xs) else throw new IllegalArgumentException
  }

  def findKthElement1[A](n: Int, ls: List[A]): A =
    if (n >= 0) ls(n)
    else throw new NoSuchElementException

  // Not that much harder without.
  def findKthElement2[A](n: Int, ls: List[A]): A = (n, ls) match {
    case (0, h :: _) => h
    case (n, _ :: tail) => findKthElement2(n - 1, tail)
    case (_, Nil) => throw new NoSuchElementException
  }

  def findKthElement3[A](n: Int, ls: List[A]): A =ls.drop(2).head

  def findKthElement4[A](n: Int, ls: List[A]): A =ls.splitAt(n)._2.head

  def findKthElement5[A](n: Int, ls: List[A]): A =ls.take(n+1).last

  println(findKthElement(2, List(1, 2, 3, 4, 5)))
  println(findKthElement1(2, List(1, 2, 3, 4, 5)))
  println(findKthElement2(2, List(1, 2, 3, 4, 5)))
  println(findKthElement3(2, List(1, 2, 3, 4, 5)))
  println(findKthElement4(2, List(1, 2, 3, 4, 5)))
  println(findKthElement5(2, List(1, 2, 3, 4, 5)))
}
