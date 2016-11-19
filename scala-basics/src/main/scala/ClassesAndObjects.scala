/**
  * Created by govind.bhone on 11/18/2016.
  */
class Person(name: String, age: Int) {
  println("In Person primary constructor")

  def display(): Unit = {
    println("welcome to person class ")
  }

  override def toString = "[" + name + "," + age + "]"
}

class EmployeeInfo(private var name: String, private var age: Int, private var salary: Int) extends Person(name, age) {
  println("In employee primary constructor")

  def this(name: String, age: Int) {
    this(name, age, 34)
    salary = 45000
  }

  override def display(): Unit = {
    println("welcome to employee class")
  }

  override def toString = s"[ name :${name} ,age :${age} ,salary : ${salary} ]"
}

object ClassesAndObjects extends App {
  val emp = new EmployeeInfo("govind", 23, 50000)
  println(emp)
  emp.display()
  val emp2 = new EmployeeInfo("govind", 34)
  println(emp2)
  //println(emp2.age) Inaccessible
  emp2.display()

  val emp3: Person = new EmployeeInfo("ashish", 34)
  println(emp3)
  emp3.display()

  //val emp4:EmployeeInfo=new Person("sachin",45)   //Error

}
