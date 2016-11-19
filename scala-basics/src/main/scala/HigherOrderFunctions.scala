/**
  * Created by govind.bhone on 11/19/2016.
  */
object HigherOrderFunctions extends App{

  trait Op {
    val square:Int=>Int=x=>x*x
  }
  trait NumericOps[T] extends Op{
    def op(f:T=>T,v:T)=f(v)
    def fun=square
  }

  case object NumericInt extends NumericOps[Int]
  case object NumericLong extends NumericOps[Long]
  case object NumericDouble extends NumericOps[Double]

  import NumericInt._
  println(op(fun,2))
  println(op(fun,2))
}
