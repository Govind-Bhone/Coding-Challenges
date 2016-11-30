/**
  * Created by govind.bhone on 11/29/2016.
  */
object P13 extends App {

  // P13 (**) Run-length encoding of a list (direct solution).
  //     Implement the so-called run-length encoding data compression method
  //     directly.  I.e. don't use other methods you've written (like P09's
  //     pack); do all the work directly.
  //
  //     Example:
  //     scala> encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //     res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

  def calcRunlengthEncoding[T](xs: List[T]): List[(Int, T)] = {
    xs match {
      case Nil => Nil
      case xh :: tail => val (packed, next) = xs.span(_ == xh)
        (packed.length, xh) :: calcRunlengthEncoding(next)
    }
  }

  def calcRunlengthEncoding1[T](xs: List[T]): List[(Int, T)] = {
    xs match {
      case Nil => Nil
      case xh :: tail =>(xs.takeWhile(_==xh).length, xh) :: calcRunlengthEncoding(xs.dropWhile(_==xh))
    }
  }


  assert(calcRunlengthEncoding(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))==List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
  assert(calcRunlengthEncoding1(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))==List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
}
