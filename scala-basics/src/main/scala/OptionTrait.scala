/**
  * Created by govind.bhone on 11/20/2016.
  */
object OptionTrait extends App {

  case class User(
                   id: Int,
                   firstName: String,
                   lastName: String,
                   age: Int,
                   gender: Option[String])

  object UserRepository {
    private val users = Map(1 -> User(1, "John", "Doe", 32, Some("male")),
      2 -> User(2, "Johanna", "Doe", 30, None))

    def findById(id: Int): Option[User] = users.get(id)

    def findAll = users.values
  }

  val user1 = UserRepository.findById(1)
  if (user1.isDefined) {
    println(user1.get.firstName)
  } // will print "John"

  val user = User(2, "Johanna", "Doe", 30, None)
  println("Gender: " + user.gender.getOrElse("not specified")) // will print "not specified"


  //===================Mapping Option ========================
  val age = UserRepository.findById(1).map(_.age) // age is Some(32)
  println(age)
  println(UserRepository.findById(1).map(_.gender))

  //====================FlatMapping Option
  val gender =UserRepository.findById(1).flatMap(_.gender)
  println(gender)

  val names: List[List[String]] =
    List(List("John", "Johanna", "Daniel"), List(), List("Doe", "Westheide"))
  names.map(_.map(_.toUpperCase))
  // results in List(List("JOHN", "JOHANNA", "DANIEL"), List(), List("DOE", "WESTHEIDE"))
  names.flatMap(_.map(_.toUpperCase))
  // results in List("JOHN", "JOHANNA", "DANIEL", "DOE", "WESTHEIDE")

  println(names.map(_.map(_.toUpperCase)).flatten)
  // results in List("JOHN", "JOHANNA", "DANIEL", "DOE", "WESTHEIDE")

  val names1: List[Option[String]] = List(Some("Johanna"), None, Some("Daniel"))
  names1.map(_.map(_.toUpperCase)) // List(Some("JOHANNA"), None, Some("DANIEL"))
  names1.flatMap(xs => xs.map(_.toUpperCase)) // List("JOHANNA", "DANIEL")

  for {
    User(_, _, _, _, Some(gender)) <- UserRepository.findAll
  } yield gender

  case class Resource(content: String)
  val resourceFromConfigDir: Option[Resource] = None
  val resourceFromClasspath: Option[Resource] = Some(Resource("I was 1st found on the classpath"))
  val resourceFromClasspath1: Option[Resource] = Some(Resource("I was 2nd found on the classpath"))
  val resource = resourceFromConfigDir orElse resourceFromClasspath orElse resourceFromClasspath1
  println(resource)

}
