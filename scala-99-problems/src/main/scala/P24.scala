import scala.util.Random

/**
  * Created by govind.bhone on 12/2/2016.
  */
object P24 extends App {

  // P24 (*) Lotto: Draw N different random numbers from the set 1..M.
  //     Example:
  //     scala> lotto(6, 49)
  //     res0: List[Int] = List(23, 1, 17, 33, 21, 37)

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

  def generateN(max:Int)=List.range(1,max+1)

  def lotto(n:Int,max:Int)=randomSelect(n,generateN(max))

  println(lotto(6,49))

}
