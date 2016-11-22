/**
  * Created by govind.bhone on 11/22/2016.
  */
object FizzBuzzProblem extends App{

  def fizzBuzzProblem(n: Int) = {
    (n % 3 == 0, n % 5 == 0) match {
      case (true, true) => println("fizzbuzz")
      case (true, false) => println("fizz")
      case (false, true) => println("buzz")
      case (false, false) => println(n)
    }
  }

  (1 to 10000).foreach(f => fizzBuzzProblem(f))

  object FizzBuz {

    def fizzbuzz(l: List[String], n: Int, s: String) = if (l.head.toInt % n == 0) l :+ s else l

    def fizz(l: List[String]) = fizzbuzz(l, 3, "Fizz")

    def buzz(l: List[String]) = fizzbuzz(l, 5, "Buzz")

    def headOrTail(l: List[String]) = if (l.tail.size == 0) l.head else l.tail.mkString

  }
  import FizzBuz._

  Range(1, 1000).take(100).map(n => List(n.toString)).map(fizz).map(buzz).map(headOrTail).foreach(println)

  def eval(x: Int) = (x % 3, x % 5, x) match {
    case(0, 0, _) => "FizzBuzz"
    case(0, _, _) => "Fizz"
    case(_, 0, _) => "Buzz"
    case _ => "" + x
  }

}
