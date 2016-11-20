/**
  * Created by govind.bhone on 11/20/2016.
  */
object DefaultParameterAndNamedValues extends App{


  def increment(x:Int,incrementFactor:Int=1): Int ={
    x+incrementFactor
  }


  println(increment(45))
  println(increment(45,5))

  def `%` (x:Int,y:Int)= x % y

  println(%(2,3))
  println(%(x=3,y=2))
  println(%(y=3,x=2))
}
