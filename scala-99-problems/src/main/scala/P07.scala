import scala.collection.GenTraversableOnce
import scala.collection.generic.CanBuildFrom

/**
  * Created by govind.bhone on 11/24/2016.
  *
  * http://typelevel.org/projects/
  */
object P07 extends App {


  def flattenList1(ls: List[Any]): List[Any] = ls flatMap { f => val res =f match {
    case ms: List[_] => println("list is " + ms); flattenList1(ms)
    case e => println(e); List(e)
  }
    println("result is "+res);res
  }

  println(flattenList1(List(11, List(1, 2, 3), List(6, 7, 8, 9), 1, 10, List(10))))

  println(List(List(1,2,3)).seq)

}
