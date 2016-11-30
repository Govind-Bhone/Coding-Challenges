/**
  * Created by govind.bhone on 11/24/2016.
  */
object P05 extends App {

  def reverse[T](xs: List[T]): List[T] = {
    var that: List[T] = xs
    var these: List[T] = Nil
    while (!that.isEmpty) {
      these = that.head :: these
      that = that.tail
    }
    these
  }

  // Simple recursive.  O(n^2)  but have more complexity
  def reverseRecursive[A](ls: List[A]): List[A] = ls match {
    case Nil       => Nil
    case h :: tail => reverseRecursive(tail) ::: List(h)
  }

  // Tail recursive with accumulator.
  def reverseTailRecursive[A](ls: List[A]): List[A] = {
    def reverseR(result: List[A], curList: List[A]): List[A] = curList match {
      case Nil       => result
      case h :: tail => reverseR(h :: result, tail)
    }
    reverseR(Nil, ls)
  }


  def reverse1[T](xs: List[T]): List[T]=xs.reverse

  def reverse2[T](xs: List[T]): List[T]=xs.foldLeft(List[T]()){(acc,c)=>acc.::(c)}

 // def reverse3[T](xs: List[T]): List[T]=xs.foldRight(List[T]()){(c,acc)=>acc.::(c)}

  assert(reverse(List(1, 2, 3, 4, 5, 6))==List(6,5,4,3,2,1))
  assert(reverse1(List(1, 2, 3, 4, 5, 6))==List(6,5,4,3,2,1))
  assert(reverse2(List(1, 2, 3, 4, 5, 6))==List(6,5,4,3,2,1))
  assert(reverseTailRecursive(List(1, 2, 3, 4, 5, 6))==List(6,5,4,3,2,1))
  assert(reverseRecursive(List(1, 2, 3, 4, 5, 6))==List(6,5,4,3,2,1))
 // assert(reverse3(List(1, 2, 3, 4, 5, 6)))
}
