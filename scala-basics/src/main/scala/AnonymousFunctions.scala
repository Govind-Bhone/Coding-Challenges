/**
  * Created by govind.bhone on 11/19/2016.
  */
object AnonymousFunctions extends App {

  val fun1 = new Function1[Int, Int] {
    def apply(x: Int) = x + 1
  }
  println(fun1(23))

  val fun2 = (x: Int) => x + 1
  println(fun2(23))

  val fun4: Int => Int = _ + 1
  println(fun4(23))

  val fun5 = (i: Int) => {
    i + 1
  }
  println(fun5(23))

  val fun6: Int => Int = x => x + 1
  println(fun6(23))

  def modMethod(i: Int) = i % 2 == 0

  def modMethod1(i: Int) = {
    i % 2 == 0
  }

  def modMethod2(i: Int): Boolean = i % 2 == 0

  def modMethod3(i: Int): Boolean = {
    i % 2 == 0
  }

  val list = List.range(1, 10)
  list.filter(modMethod)


  //==================Assigning an existing function/method to a function variable===========

  val c = scala.math.sin _
  println(c(90))

  val p = scala.math.pow(_, _)
  println(p(scala.math.E, 2))

}
