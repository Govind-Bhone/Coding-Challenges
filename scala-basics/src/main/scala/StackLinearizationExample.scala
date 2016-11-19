/**
  * Created by govind.bhone on 11/19/2016.
  */

object StackLinearizationExample extends App {


  //  FourLegged -> Base -> AnyRef -> Any
  //  SixLegged -> TwoLegged -> FourLegged -> Base -> AnyRef -> Any
  //  SixLegged -> TwoLegged -> FourLegged -> Base -> AnyRef -> Any
  //  D -> SixLegged -> TwoLegged -> FourLegged -> Base -> AnyRef -> Any



  trait WithLegs {
    def legs: String = {
      println("Class Base")
      "Base"
    }
  }

  trait TwoLegged extends WithLegs {
    override def legs: String = {
      println("class TwoLegged")
      "Two -> " + super.legs
    }
  }

  trait FourLegged extends WithLegs {
    override def legs: String = {
      println("Class FourLegged")
      "Four -> " + super.legs
    }
  }

  trait SixLegged extends TwoLegged {
    override def legs: String = {
      println("Class SixLegged")
      "Six -> " + super.legs
    }
  }

  class A extends TwoLegged with FourLegged {
    override def legs = {
      println("Class A")
      "A -> " + super.legs
    }
  }

  class B extends FourLegged with TwoLegged {
    override def legs = {
      println("class B")
      "B -> " + super.legs

    }
  }

  class C extends SixLegged with FourLegged with TwoLegged {
    override def legs = {
      println("class C")
      "C -> " + super.legs
    }
  }

  class D extends FourLegged with SixLegged with TwoLegged {
    override def legs = {
      println("class D")
      "D -> " + super.legs
    }
  }

  //  new A().legs
  //  new B().legs
  //  new C().legs
  println(new D().legs)
}