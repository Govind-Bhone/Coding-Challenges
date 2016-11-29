/**
  * Created by govind.bhone on 11/27/2016.
  */
object Day28 {

  case class EmailInfo(firstName: String, emailId: String)

  val emailBuffer = new scala.collection.mutable.ArrayBuffer[EmailInfo]
  val gmailIdRegEx ="""[a-z]+@gmail.com""".r

  def filterEmailTable =
    emailBuffer
      .sortBy(_.firstName)
      .filter(user => gmailIdRegEx.findFirstIn(user.emailId) != None).foreach(f => println(f.firstName))


  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    var N = sc.nextInt()
    var a0 = 0
    while (a0 < N) {
      var firstName = sc.next()
      var emailID = sc.next()
      emailBuffer.+=(EmailInfo(firstName, emailID))
      a0 += 1
    }
    filterEmailTable
  }

}
