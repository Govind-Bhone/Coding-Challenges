/**
  * Created by govind.bhone on 11/20/2016.
  */
object ImplicitParameters extends App {

  abstract class SemiGroup[A] {
    def add(x: A, y: A): A
  }

  abstract class Monoid[A] extends SemiGroup[A] {
    def unit: A
  }

  object ImplicitTest {

    /** To show how implicit parameters work, we first define monoids for strings and integers.
      * The implicit keyword indicates that the corresponding object can be used implicitly,
      * within this scope, as a parameter of a function marked implicit. */
    implicit object StringMonoid extends Monoid[String] {
      def add(x: String, y: String): String = x concat y

      def unit: String = ""
    }

    implicit object IntMonoid extends Monoid[Int] {
      def add(x: Int, y: Int): Int = x + y

      def unit: Int = 0
    }

    /** This method takes a List[A] returns an A which
      * represent the combined value of applying the monoid operation successively across the whole list.
      * Making the parameter m implicit here means we only have to provide the xs parameter at the call site, since if we have a List[A] we know what type A
      * actually is and therefore what type Monoid[A] is needed. We can then implicitly find whichever val or object in the current scope also has that type and use that without needing to specify it explicitly. */
    def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
      if (xs.isEmpty) m.unit
      else m.add(xs.head, sum(xs.tail))

  }

  import ImplicitTest._

  //importing implicit definitions explicitly

  /** Here we call sum twice, with only one parameter each time. Since the second parameter of sum, m, is implicit its value is looked up in the current scope, based on the type of monoid required in each case, meaning both expressions can be fully evaluated. */
  println(sum(List(1, 2, 3))) // uses IntMonoid implicitly
  println(sum(List("a", "b", "c")))

  // uses StringMonoid implicitly


  def speakImplicitly(implicit greeting: String) = println(greeting)

  implicit val aUnit = ()
  implicit val aString = "Hello World"
  implicit val aDouble = 3.45

  speakImplicitly

  implicit val hello: Any = "Hello world"

  speakImplicitly //no ambiguity as it work on static typing

  //implicit val hello1 :String ="Hello world"    //create ambiquity

  speakImplicitly


  def sayThings(implicit args: List[Any]) = args.foreach(println(_))

  implicit val nothingNiceToSay: List[Any] = Nil

  // sayThings

  implicit val h: List[String] = List("Hello world using subtype implicit value")

  sayThings

  // If the parameter of method  is supertype of the implicit value defined in scope then that value get used
  // If the parameter is subtype of the implicit value present in scope then it never get used

  //=================================Parameterized implicits==========================

  def parameterImplicitly[T](implicit t: T) = println(t)

  implicit val foo = ("foo", "bar")
  implicit val flt = 4.5f

  parameterImplicitly[Tuple2[String, String]]
  parameterImplicitly[Float]

  implicit def sbl = Symbol("foo")

  parameterImplicitly[Symbol]

  implicit def emptyList[T]: List[T] = Nil;

  parameterImplicitly[List[String]]
  parameterImplicitly[List[Long]]

  //=======================Implicit definition with case classes

  def implicitly[T](implicit t: T) = t

  case class Foo[T](t: T)

  implicit val valueChar: Char = 'F'

  implicit def foo[T](implicit t: T): Foo[T] = Foo[T](t)

  println(foo[Char])
  // println(implicitly[Foo[Char]])

  object TwoParam{
    implicit val code:Byte= 7
    //implicit val greeting = "Hello"

    def greet(implicit greeting: String, code: Byte) = s"$greeting, 00$code!"
  }

  import TwoParam._
  println(TwoParam.greet)


}
