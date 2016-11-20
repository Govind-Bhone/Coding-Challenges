/**
  * Created by govind.bhone on 11/19/2016.
  */
object CaseClasses extends App {

  sealed trait Person {
    def title: String
  }

  case class Employer(name: String) extends Person {
    override def title = "Employer"
  }

  //Using beloww mutation is discouraged
  case class Employee(var name: String) extends Person {
    name = "govind"
    var age: Int = _

    override def title = "Employee"

    def this(name: String, age: Int) {
      this(name)
      this.name = name
      this.age = age
    }

  }


  var emp1 = Employee("ashish")
  println(emp1)

  emp1 = emp1.copy(name = "rahul")
  //but create new object

  val emp2 = new Employee("sachin", 23) //Need to use new keyword
  println(emp2)

  val employer1: Person = Employer("sachin")


  employer1 match {
    case a@Employee(name) => //a.name="sachin"--possible
      println(a.title)
    case a@Employer(name) => //a.name="sachin" --not possible
      println(a.title)
  }

  //object Equality as object structure and not reference
  val emp3 = emp2
  println(emp3.==(emp2))
  val emp4 = new Employee("sachin", 23)
  println(emp4.==(emp2))
}
