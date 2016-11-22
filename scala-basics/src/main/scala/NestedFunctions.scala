/**
  * Created by govind.bhone on 11/19/2016.
  */
object NestedFunctions extends App {

  def filter[T](xs: List[T], comparatorFun: T => Boolean): List[T] = {
    def filterLogic(xs: List[T]): List[T] = {
      xs match {
        case Nil => Nil
        case xh :: xt => println(xh);if (comparatorFun(xh)) xh :: filterLogic(xs.tail) else filterLogic(xs.tail)
      }
    }
    filterLogic(xs)
  }

  println(filter[Int](List(1,2,3),_ % 2==0))
  println(filter[String](List("govind","bhone","ram","seth"),_.length >5))

}
