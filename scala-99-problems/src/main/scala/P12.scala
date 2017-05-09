/**
  * Created by govind.bhone on 11/29/2016.
  */
object P12 extends App {
  // P12 (**) Decode a run-length encoded list.
  //     Given a run-length code list generated as specified in problem P10,
  //     construct its uncompressed version.
  //
  //     Example:
  //     scala> decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
  //     res0: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

  def decodeRunLengthEncoding[T](xs: List[(Int, T)]): List[T] = xs.flatMap(t => List.fill(t._1) {
    t._2
  })

  def decodeRunLengthEncoding1[T](xs: List[(Int, T)]): List[T] = xs match {
    case Nil => Nil
    case xh :: tail => if (xh._1 == 1) xh._2 :: decodeRunLengthEncoding(tail)
    else List.fill(xh._1) {
      xh._2
    } ::: decodeRunLengthEncoding(tail)
  }

  def decodeRunLengthEncoding2[T](xs: List[(Int, T)]): List[T] = xs.flatMap(t => List.fill(t._1)(t._2))

  def decodeRunLengthEncoding3[T](xs: List[(Int, T)]): List[T] = xs.flatMap(t => for (i <- 1 to t._1) yield t._2)


  assert(decodeRunLengthEncoding(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))==List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  assert(decodeRunLengthEncoding1(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))==List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  assert(decodeRunLengthEncoding2(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))==List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  assert(decodeRunLengthEncoding3(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))==List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
}
