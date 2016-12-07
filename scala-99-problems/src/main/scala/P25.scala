import scala.reflect.ClassTag
import scala.util.Random

/**
  * Created by govind.bhone on 12/2/2016.
  */
object P25 extends App {

  // P25 (*) Generate a random permutation of the elements of a list.
  //     Hint: Use the solution of problem P23.
  //
  //     Example:
  //     scala> randomPermute(List('a, 'b, 'c, 'd, 'e, 'f))
  //     res0: List[Symbol] = List('b, 'a, 'd, 'c, 'e, 'f)

  def removeAt1[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
    case (Nil, _) if n < 0 => throw new NoSuchElementException
    case (pre, e :: post) => (pre ::: post, e)
    case (pre, Nil) => throw new NoSuchElementException
  }

  //creating only one instance of scala.util.Random here
  def randomSelect[A](n: Int, ls: List[A]): List[A] = {
    def loop(n: Int, ls: List[A], randomFun: scala.util.Random): List[A] = {
      if (n <= 0) Nil
      else {
        val (rest, e) = removeAt1(randomFun.nextInt(ls.length), ls)
        e :: randomSelect(n - 1, rest)
      }
    }
    loop(n, ls, new Random())
  }

  def randomPermute[A](xs: List[A]): List[A] = randomSelect(xs.length, xs)

  println(randomPermute(List('a, 'b, 'c, 'd, 'e, 'f)))

  // The canonical way to shuffle imperatively is Fisher-Yates.  It requires a
  // mutable array.  This is O(n).
  def randomPermute1[A](ls: List[A])(implicit c: ClassTag[A]): List[A] = {
    val rand = new util.Random
    val a = ls.toArray
    for (i <- a.length - 1 to 1 by -1) {
      val i1 = rand.nextInt(i + 1)
      val t = a(i)
      a.update(i, a(i1))
      a.update(i1, t)
    }
    a.toList
  }
  println(randomPermute1(List('a, 'b, 'c, 'd, 'e, 'f)))

}
