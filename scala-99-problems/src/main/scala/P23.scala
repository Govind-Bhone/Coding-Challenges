import scala.util.Random

/**
  * Created by govind.bhone on 12/2/2016.
  */
object P23 extends App {

  // P23 (**) Extract a given number of randomly selected elements from a list.
  //     Example:
  //     scala> randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h))
  //     res0: List[Symbol] = List('e, 'd, 'a)
  //
  //     Hint: Use the answer to P20.

  def removeAt1[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
    case (Nil, _) if n < 0 => throw new NoSuchElementException
    case (pre, e :: post) => (pre ::: post, e)
    case (pre, Nil) => throw new NoSuchElementException
  }

  def randomSelect[A](n: Int, ls: List[A]): List[A] = {
    if (n <= 0) Nil
    else {
      val (rest, e) = removeAt1(scala.util.Random.nextInt(ls.length), ls)
      e :: randomSelect(n - 1, rest)
    }
  }

  //creating only one instance of scala.util.Random here
  def randomSelect1[A](n: Int, ls: List[A]): List[A] = {
    def loop(n: Int, ls: List[A], randomFun: scala.util.Random): List[A] = {
      if (n <= 0) Nil
      else {
        val (rest, e) = removeAt1(randomFun.nextInt(ls.length), ls)
        e :: randomSelect1(n - 1, rest)
      }
    }
    loop(n, ls, new Random())
  }

  println(randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h)))
  println(randomSelect1(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h)))
}
