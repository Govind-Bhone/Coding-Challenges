/**
  * Created by govind.bhone on 11/20/2016.
  */
object P01 extends App {

  def last[T](xs: List[T]): T = {
    xs match {
      case Nil => throw new NoSuchElementException("List is empty")
      case h :: Nil => h
      case _ :: tail => last(tail)
    }
  }

  println(last(List(1, 2, 3, 4, 5)))

}
