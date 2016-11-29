/**
  * Created by govind.bhone on 11/29/2016.
  */
object P15 extends App {
  // P15 (**) Duplicate the elements of a list a given number of times.
  //     Example:
  //     scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
  //     res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)

  def duplicateNtimes[T](n: Int, xs: List[T]): List[T] = {
    xs match {
      case Nil => Nil
      case xh :: tail => List.make(n, xh) ::: duplicateNtimes(n, tail)
    }
  }

  def duplicateNtimes1[T](n: Int, xs: List[T]): List[T] = xs.flatMap(f => for (i <- 1 to n) yield f)

  def duplicateNtimes2[T](n: Int, xs: List[T]): List[T] = {
    def loop(xs: List[T]): List[T] = {
      xs match {
        case Nil => Nil
        case xh :: tail => List.fill(n) {
          xh
        } ::: loop(tail)
      }
    }
    loop(xs)
  }

  println(duplicateNtimes(3,List('a, 'b, 'c, 'c, 'd)))
  println(duplicateNtimes1(3,List('a, 'b, 'c, 'c, 'd)))
  println(duplicateNtimes2(3,List('a, 'b, 'c, 'c, 'd)))


}
