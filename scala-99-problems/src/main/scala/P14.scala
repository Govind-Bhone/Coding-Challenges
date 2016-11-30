/**
  * Created by govind.bhone on 11/29/2016.
  */
object P14 extends App {
  // P14 (*) Duplicate the elements of a list.
  //     Example:
  //     scala> duplicate(List('a, 'b, 'c, 'c, 'd))
  //     res0: List[Symbol] = List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)

  def duplicate[T](xs: List[T]): List[T] = {
    xs match {
      case Nil => Nil
      case xh :: tail => xh :: xh :: duplicate(tail)
    }
  }

  def duplicate1[T](xs: List[T]): List[T] = xs.flatMap(f => List(f, f))

  def duplicate2[T](xs: List[T]): List[T] = {
    xs match {
      case Nil => Nil
      case xh :: tail => List.fill(2) {
        xh
      } ::: duplicate(tail)
    }
  }

  assert(duplicate(List('a, 'b, 'c, 'c, 'd))==List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
  assert(duplicate1(List('a, 'b, 'c, 'c, 'd))==List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
  assert(duplicate2(List('a, 'b, 'c, 'c, 'd))==List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
}
