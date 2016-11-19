/**
  * Created by govind.bhone on 11/18/2016.
  */
object ImplicitOrderingForObjects extends App {

  //==========================Approach 1 ==========================
  trait Number {
    val number: Int
  }

  case class Integer(override val number: Int) extends Number

  case class Long(override val number: Int) extends Number

  object Scope {
    implicit def ordering: Ordering[Number] = new Ordering[Number] {
      override def compare(o1: Number, o2: Number): Int = {
        if (o1.number < o2.number) 1
        else -1
      }
    }
  }

  import Scope._

  val list = List(Integer(10), Long(20), Integer(45), Long(2)).sorted(ordering)
  println(list)

  //=========================Approach 2================================
  case class A(tag: String, load: Int) extends Ordered[A] {

    import scala.math.Ordered.orderingToOrdered

    def compare(that: A): Int = (this.tag, this.load) compare(that.tag, that.load)
  }

  val tagList = List(("one", 1), ("two", 2), ("three", 3)).sorted
  println(tagList)

  //==========================Approach 3 ==================================
  case class Employee(id: Int, firstName: String, lastName: String)

  object Employee {

    implicit val orderingById: Ordering[Employee] = Ordering.by(e => e.id)

    implicit def orderingByName[A <: Employee]: Ordering[A] =
      Ordering.by(e => (e.lastName, e.firstName))
  }

  import Employee._

  val elist = List(Employee(101, "govind", "bhone"), Employee(2, "sachin", "chavan"), Employee(99, "Ashish", "Nagdev"))
  println(elist.sorted)


  //=====================Implicit Conversion========================

  println("123".map(_.toInt))

  //================= implicit conversion and an implicit parameter.=================
  def getIndex[T, CC](seq: CC, value: T)(implicit conv: CC => Seq[T]) = seq.indexOf(value)

  getIndex("abc", 'a')

  //behind the scenes it will be conv(seq).indexOf(value)

  //def getIndex[T, CC <% Seq[T]](seq: CC, value: T) = seq.indexOf(value)

  //========================context bound ===========================
  def sum[T: Integral](list: List[T]): T = {
    val integral = implicitly[Integral[T]]
    import integral._
    // get the implicits in question into scope
    list.foldLeft(integral.zero)(_ + _)
  }

  def reverseSort[T: Ordering](seq: Seq[T]) = seq.sorted.reverse


  //=====================define implicit in current scope

  implicit val x = 5

  def sum(y: Int)(implicit x: Int) = {
    x + y
  }

  println(sum(10))

  class B(val n: Int) {
    def +(other: B) = new B(n + other.n)
  }

  object B {
    implicit def fromInt(n: Int) = new B(n)
  }

  // This becomes possible:
  1 + new B(1)
  // because it is converted into this:
  B.fromInt(1) + new B(1)


  //======================Explicit conversions==========
  import scala.collection.JavaConversions.mapAsScalaMap

  def env = System.getenv() // Java map
  // implicit conversion from Java Map to Scala Map
  //val e1= env("PATH")



}

