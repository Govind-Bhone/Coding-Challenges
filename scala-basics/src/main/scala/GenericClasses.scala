/**
  * Created by govind.bhone on 11/19/2016.
  */
object GenericClasses extends App {

  class Stack[T] {
    var elms: List[T] = Nil

    def push(ele: T): Unit = elms = ele :: elms

    def pop(): T = {
      val head = top
      elms = elms.tail
      head
    }

    def top: T = elms.head
  }

  val stack = new Stack[Int]()
  stack.push(10)
  stack.push(20)
  println(stack.pop())
  stack.push(40)
  stack.push(50)
  println(stack.elms)
  println(stack.top)

  object Math {

    import annotation.implicitNotFound

    @implicitNotFound("No member of type class NumberLike in scope for ${T}")
    trait NumberLike[T] {
      def plus(x: T, y: T): T

      def divide(x: T, y: Int): T

      def minus(x: T, y: T): T
    }

  }

  object Statistics {
    import Math.NumberLike
    def mean[T](xs: Vector[T])(implicit ev: NumberLike[T]): T =
      ev.divide(xs.reduce(ev.plus(_, _)), xs.size)
    def median[T : NumberLike](xs: Vector[T]): T = xs(xs.size / 2)
    def quartiles[T: NumberLike](xs: Vector[T]): (T, T, T) =
      (xs(xs.size / 4), median(xs), xs(xs.size / 4 * 3))
    def iqr[T: NumberLike](xs: Vector[T]): T = quartiles(xs) match {
      case (lowerQuartile, _, upperQuartile) =>
        implicitly[NumberLike[T]].minus(upperQuartile, lowerQuartile)
    }
  }


}
