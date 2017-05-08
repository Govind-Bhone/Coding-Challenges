/**
  * Created by govind.bhone on 3/8/2017.
  */
object Solution extends App{
  println("Hello world")



  def sum(acc:Int,ele:Int)=acc+ele

  println(List(1,2,3,4,5,6).reduceLeft((acc,ele)=>sum(acc,ele)))
  println(List(1,2,3,4,5,6).reduceLeft(sum))
  println(List(1,2,3,4,5,6).reduceLeft(_+_))

  println(List(1,2,3,4,5,6).reduceLeft((acc,ele)=>acc+ele))

  println(List(1,2,3,4,5,6).reduceLeft[Int]{
    case (acc,ele)=> acc+ele
  })

  // Below thing didn't work
//  def func(num1 : Int, num2 : Int, num3 : Int) : Int = { num1+num2*num3}
//
//  List(1,2,3,4,5).reduceLeft(func);



  case class EmpSalary (basic:Double,hra : Double , bonus:Double)

  def totalSalary(totalresult:Double,empDetails:EmpSalary):Double = empDetails.basic + empDetails.hra +0.5 * empDetails.bonus

 /* List(EmpSalary(200,34,556),EmpSalary(456,678,890)).reduceLeft(totalSalary)*/




  /**
    * Created by govind.bhone on 11/19/2016.
    */


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


      println("hello")
      println(new D().legs)


    //  FourLegged -> Base -> AnyRef -> Any
    //  SixLegged -> TwoLegged -> FourLegged -> Base -> AnyRef -> Any
    //  SixLegged -> TwoLegged -> FourLegged -> Base -> AnyRef -> Any
    //  D -> SixLegged -> TwoLegged -> FourLegged -> Base -> AnyRef -> Any



    //  new A().legs
    //  new B().legs
    //  new C().legs


}

