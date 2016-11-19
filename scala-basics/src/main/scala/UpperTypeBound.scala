/**
  * Created by govind.bhone on 11/19/2016.
  */
object UpperTypeBound extends App {

  //=============================Example 1=======================
  trait Similar {
    def isSimilar(x: Any): Boolean
  }

  case class MyInt(x: Int) extends Similar {
    override def isSimilar(m: Any): Boolean = m.isInstanceOf[MyInt] && m.asInstanceOf[MyInt].x == x
  }

  def findSimilar[T <: Similar](e: T, xs: List[T]): Boolean = {
    if (xs.isEmpty) false
    else if (e.isSimilar(xs.head)) true
    else findSimilar[T](e, xs.tail)
  }


  val list: List[MyInt] = List(MyInt(1), MyInt(2), MyInt(3))
  println(findSimilar[MyInt](MyInt(4), list))
  println(findSimilar[MyInt](MyInt(2), list))


  //=======================Example 2========================================

  abstract class Animal {
    def name: String
  }

  abstract class Pet extends Animal {}

  class Cat extends Pet {
    override def name: String = "Cat"
  }

  class Dog extends Pet {
    override def name: String = "Dog"
  }

  class Lion extends Animal {
    override def name: String = "Lion"
  }

  class Cage[P <: Pet](p: P) {
    def pet: P = p
  }

  object Main extends App {
    var dogCage = new Cage[Dog](new Dog)
    var catCage = new Cage[Cat](new Cat)
    /* Cannot put Lion in a cage as Lion is not a Pet. */
    //  var lionCage = new Cage[Lion](new Lion)
  }

  def sum[T <: Int](x: T, y: T) = x + y  //without upper bound it will not work for + operation
                                         //Because not enough information for executing jvm rule
                                         //weather need to append both or to addition
  println(sum[Int](2, 3))


}
