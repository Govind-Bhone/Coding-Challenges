import java.text.SimpleDateFormat

/**
  * Created by govind.bhone on 11/19/2016.
  */
object RegularExpression extends App{
  val numPattern = "[0-9]+".r
  val address = "123 Main Street Suite 101"
  val match1 = numPattern.findFirstIn(address)
  println(match1)
  val matches = numPattern.findAllIn(address)
  matches.foreach(println)

  import scala.util.matching.Regex

  val numPattern1=new Regex("[0-9]+")
  println(numPattern1.findFirstIn(address))

  numPattern1.findFirstIn(address) match {
    case Some(s) => println(s"Found: $s")
    case None =>
  }

  //==================RegEx capturing grouping

  val Date = """(\d\d)/(\d\d)/(\d\d\d\d)""".r

  val df=new SimpleDateFormat("dd/mm/yyyy")
  val javaDate =new java.util.Date()
  println(df.format(javaDate))

  val Date(date,month,year)=df.format(javaDate)
  println(date)
  df.format(javaDate) match {
    case Date(date,month,year)=>println(date)
    case _=>println("no match")
  }

}
