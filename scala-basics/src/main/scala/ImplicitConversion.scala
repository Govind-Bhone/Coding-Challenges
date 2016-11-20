/**
  * Created by govind.bhone on 11/20/2016.
  */
object ImplicitConversion extends App {

  import scala.language.implicitConversions

  implicit def int2String(x: Int): String = x.toString

  val x = 5
  val s = x concat "4"

  println(s)

  /*  implicit def list2ordered[A](x: List[A])
                                (implicit elem2ordered: A => Ordered[A]): Ordered[List[A]] =
      new Ordered[List[A]] { /* .. */ }

    implicit def int2ordered(x: Int): Ordered[Int] =
      new Ordered[Int] { /* .. */ }
  */
}
