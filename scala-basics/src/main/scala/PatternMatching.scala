/**
  * Created by govind.bhone on 11/19/2016.
  */
object PatternMatching extends App{
  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }
  println(matchTest(3))

  def toYesOrNo(choice: Int): String = choice match {
    case 1 | 2 | 3 => "yes"
    case 0 => "no"
    case other => "error"
  }
  println(toYesOrNo(1))
  println(toYesOrNo(5))

  def f(x: Any): String = x match {
    case i:Int => "integer: " + i
    case _:Double => "a double"
    case s:String => "I want to say " + s
    case (x,y)=>"tuple"
    case Seq(x,_*) if x==2=>"Sequence"
  }
  println(f("govind"))
  println((4,5))

  def fact(n: Int): Int = n match {
    case 0 => 1
    case n => n * fact(n - 1)
  }

  def factFold(n:Int) = (1 to n).foldLeft(1) { _ * _ }


  def length[A](list : List[A]) : Int = list match {
    case _ :: tail => 1 + length(tail)
    case Nil => 0
  }
  println(length(List(1,2,3,4,5)))


  //Compile to TableSwitch or lookup switch for performance rather than branch table
  // Version 1 - compiles to a tableswitch
  import scala.annotation.switch

  class SwitchDemo {

    val i = 1
    val x = (i: @switch) match {
      case 1  => "One"
      case 2  => "Two"
      case _  => "Other"
    }

  }

  // Version 2 - not compiles to a tableswitch
  import scala.annotation.switch

  class SwitchDemo1 {

    val i = 1
    val two=2
    val x = (i: @switch) match {
      case 1  => "One"
      case two  => "Two"
      case _  => "Other"
    }

  }

  //===================Pattern as Value variable

  case class Player(name: String, score: Int)

  def printMessage(player: Player) = player match {
    case Player(_, score) if score > 100000 => println("Get a job, dude!")
    case Player(name, _) => println("Hey " + name + ", nice to see you again!")
  }

  //Convert above routine into less side effect

  def message(player: Player) = player match {
    case Player(_, score) if score > 100000 => "Get a job, dude!"
    case Player(name, _) => "Hey " + name + ", nice to see you again!"
  }
  def printMessage1(player: Player) = println(message(player))

  def currentPlayer(): Player = Player("Daniel", 3500)
  val player = currentPlayer()

  val Player(name, _) = currentPlayer()

}
