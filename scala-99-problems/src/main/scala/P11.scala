/**
  * Created by govind.bhone on 11/29/2016.
  */
object P11 extends App {

  // P11 (*) Modified run-length encoding.
  //     Modify the result of problem P10 in such a way that if an element has no
  //     duplicates it is simply copied into the result list.  Only elements with
  //     duplicates are transferred as (N, E) terms.
  //
  //     Example:
  //     scala> conditionalRunLengthEncoding(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //     res0: List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))

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

  def findRunLengthEncoding[T](xs: List[T]): List[(Int, T)] = packConsecutiveDuplicates(xs).map(list => (list.length, list.head))

  def conditionalRunLengthEncoding[T](xs: List[T]) = findRunLengthEncoding(xs).map(t => if (t._1 == 1) t._2 else t)

  def conditionalRunLengthEncoding1[T](xs: List[T]): List[Either[T, (Int, T)]] = findRunLengthEncoding(xs).map(t => if (t._1 == 1) Left(t._2) else Right(t))

  assert(conditionalRunLengthEncoding(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))==List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e)))
  assert(conditionalRunLengthEncoding1(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)).map(f => {
    f
    match {
      case Left(a) => a
      case Right(a) => a
    }
  }
  )==List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e)))

}
