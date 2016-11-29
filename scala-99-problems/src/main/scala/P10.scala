/**
  * Created by govind.bhone on 11/29/2016.
  */
object  P10 extends App{

  // P10 (*) Run-length encoding of a list.
  //     Use the result of problem P09 to implement the so-called run-length
  //     encoding data compression method.  Consecutive duplicates of elements are
  //     encoded as tuples (N, E) where N is the number of duplicates of the
  //     element E.
  //
  //     Example:
  //     scala> encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //     res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

  def packConsecutiveDuplicates[T](xs: List[T]): List[List[T]] = {
    xs match {
      case Nil => List(List())
      case xh1 :: tail =>
        val (packed, next) = xs span {
          _ == xs.head
        }
        if (next == Nil) List(packed)
        else packed :: packConsecutiveDuplicates(next)
    }
  }

  def findRunLengthEncoding[T](xs:List[T]): List[(Int,T)]= packConsecutiveDuplicates(xs).map(list=>(list.length,list.head))

  println(findRunLengthEncoding(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))


}
