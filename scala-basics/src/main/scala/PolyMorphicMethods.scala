/**
  * Created by govind.bhone on 11/20/2016.
  */
object PolyMorphicMethods extends App {

  def dup[T](x: T, n: Int): List[T] = {
    if (n == 0) Nil else x :: dup(x, n - 1)
  }

  case object Duplicate

  println(dup[Int](3, 4))
  println(dup(Duplicate, 4))

}
