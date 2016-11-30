/**
  * Created by govind.bhone on 11/19/2016.
  */
object Extracters extends App {

  object Twice {
    def apply(x: Int): Int = x * 2

    def unapply(z: Int): Option[Int] = if (z % 2 == 0) Some(z / 2) else None
  }

  object TwiceTest extends App {
    val x = Twice(21)
    x match {
      case Twice(n) => Console.println(n)
    } // prints 21
  }

  trait User {
    def name: String
  }

  class PrepaidUser(val name: String) extends User

  class PostPaidUser(val name: String) extends User

  object PrepaidUser {
    def apply(name: String) = new PrepaidUser(name)

    def unapply(user: PrepaidUser): Option[String] = Some(user.name)
  }

  object PostPaidUser {
    def apply(name: String) = new PostPaidUser(name)

    def unapply(user: PostPaidUser): Option[String] = Some(user.name)
  }

  val prepaidUser: User = PrepaidUser("govind")

  prepaidUser match {
    case PrepaidUser(user) => println(user)
    case PostPaidUser(user) => println(user)
  }

  println(PrepaidUser.unapply(new PrepaidUser("govind")))


  trait ScoreUser {
    def name: String

    def score: Int
  }

  class ScoreLessUser(val name: String, val score: Int, val upgradeProbability: Double)
    extends ScoreUser

  class ScoreHighUser(val name: String, val score: Int) extends ScoreUser

  object ScoreLessUser {
    def unapply(user: ScoreLessUser): Option[(String, Int, Double)] =
      Some((user.name, user.score, user.upgradeProbability))
  }

  object ScoreHighUser {
    def unapply(user: ScoreHighUser): Option[(String, Int)] = Some((user.name, user.score))
  }

  val user: ScoreUser = new ScoreLessUser("govind", 300, 0.1d)
  user match {
    case ScoreLessUser(name, _, p) =>
      if (p > 0.01) name + ", what can we do for you today?" else "Hello " + name
    case ScoreHighUser(name, _) => "Welcome back, dear " + name
  }


  //===================Boolean extracters

  val scoreUser: ScoreUser = new ScoreLessUser("govind", 300, 0.1d)

  object BooleanExtracters {
    def unapply(scoreUser: ScoreUser): Boolean = {
      if (scoreUser.score > 0.01) true else false
    }
  }

  scoreUser match {
    case freeUser@BooleanExtracters() => println(freeUser.name)
    case _ =>
  }

  val xs = 58 #:: 43 #:: 93 #:: Stream.empty
  val res = xs match {
    case first #:: second #:: _ => first - second
    case _ => -1
  }

  println(res)

  object #:: {
    def unapply[A](xs: Stream[A]): Option[(A, Stream[A])] =
      if (xs.isEmpty) None
      else Some((xs.head, xs.tail))
  }

  val xs1 = 58 #:: 43 #:: 93 #:: Stream.empty
  xs1 match {
    /*    case head #:: tail => // Infix Notation
          println(head)
          println(tail.toList)*/

    //case first #:: second #:: third=>
    case #::(first, #::(second, _)) =>
      println(first)
      println(second)
      first - second
    case _ => -1
  }

  object GivenNames {
    def unapplySeq(name: String): Option[Seq[String]] = {
      val names = name.trim.split(" ")
      if (names.forall(_.isEmpty)) None else Some(names)
    }
  }

  def greetWithFirstName(name: String) = name match {
    case GivenNames(firstName, _*) => "Good morning, " + firstName + "!"
    case _ => "Welcome! Please make sure to fill in your name!"
  }

  println(greetWithFirstName("govind bhone"))

  object Names {
    def unapplySeq(name: String): Option[(String, String, Seq[String])] = {
      val names = name.trim.split(" ")
      if (names.size < 2) None
      else Some((names.last, names.head, names.drop(1).dropRight(1).toSeq))
    }
  }

  def greet(fullName: String) = fullName match {
    case Names(lastName, firstName, _*) => "Good morning, " + firstName + " " + lastName + "!"
    case _ => "Welcome! Please make sure to fill in your name!"
  }

  println(greet("govind bhone"))


}
