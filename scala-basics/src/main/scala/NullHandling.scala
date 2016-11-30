/**
  * Created by govind.bhone on 11/22/2016.
  */
object NullHandling extends App{
  //def f():Boolean =null
 // above statement will give compilation error
  // as we can't assign null values to priimitive datatypes (AnyVal subtypes )

  def f():Option[String] =null


  f() match {
    case Some(value)=>println("some case")
    case None =>println("none case ")
    case a @ _ =>
      println("_ case "+a)
  }

  def f1(): String =null

  f1() match {
    case a @ _ =>
      println("_ case "+a.toString())
  }



}
