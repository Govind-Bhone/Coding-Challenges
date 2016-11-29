import scala.collection.GenTraversableOnce
import scala.collection.generic.CanBuildFrom

/**
  * Created by govind.bhone on 11/24/2016.
  *
  * http://typelevel.org/projects/
  */
object P07 extends App {
  def flattenList(ls: List[Any]): List[Any] = ls flatMap { f => val res =f match {
    case ms: List[_] => println("list is " + ms); flattenList(ms)
    case e => println(e); List(e)
  }
    println("result is "+res);res
  }

  println(flattenList(List(11, List(List(20),1, 2, 3), List(6, 7, 8, 9), 1, 10, List(10))))
}
