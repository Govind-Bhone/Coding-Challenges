/**
  * Created by govind.bhone on 11/20/2016.
  */
object NormalToCurriedConversion extends App{

  def sum(x:Int)(y:Int):Int={
    x+y
  }

  val sum1=sum(10) _
  val sum2=sum1(20)
  println(sum2)

  def y :Int={print ("foo") ; 10 }
  lazy val x = { print ("foo") ; 10 }
  println ("bar")
  println (x)
  println (x)
  println (y)
  println (y)

  val sum: (Int, Int) => Int = _ + _
  val sumCurried: Int => Int => Int = sum.curried

  val sumCurriedOne=sumCurried(1)
  println(sumCurriedOne(2))
  println(sum(2,3))
}
