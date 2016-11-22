/**
  * Created by govind.bhone on 11/19/2016.
  */
object SequenceComprehension extends App {
  def even(from: Int, to: Int): List[Int] =
    for (i <- List.range(from, to) if i % 2 == 0) yield i

  Console.println(even(0, 20))

  def foo(n: Int, v: Int) =
    for {
      i <- 0 until n;
      j <- i until n if i + j == v
    } yield (i, j);

  foo(20, 32) foreach {
    case (i, j) =>
    //  println(s"($i, $j)")
  }

  for (
    i <- Iterator.range(0, 20);
    j <- Iterator.range(i, 20) if i + j == 32
  )println(s"($i, $j)")
}
