import java.io.IOException

/**
  * Created by govind.bhone on 11/20/2016.
  */
object ExceptionHandling extends App {

  //========================Error Handling in Traditional way======================
  def throwsException() {
    throw new IllegalStateException("Exception thrown");
  }

  try {
    throwsException();
  } catch {
    case e: IllegalArgumentException => println("illegal arg. exception");
    case e: IllegalStateException => println("illegal state exception");
    case e: IOException => println("IO exception");
  } finally {
    println("this code is always executed");
  }


  //=======================Error Handling in Functional way =============================

  import scala.util.Try
  import java.net.URL
  def parseURL(url: String): Try[URL] = Try(new URL(url))

  val url = parseURL(Console.readLine("URL: ")) getOrElse new URL("http://google.com")
  println(url)

  parseURL("http://google.com").map(_.getProtocol)
  // results in Success("http")
  parseURL("garbage").map(_.getProtocol)
  // results in Failure(java.net.MalformedURLException: no protocol: garbage)

  import java.io.InputStream
  def inputStreamForURL(url: String): Try[Try[Try[InputStream]]] = parseURL(url).map { u =>
    Try(u.openConnection()).map(conn => Try(conn.getInputStream))
  }

  println(inputStreamForURL("http://google.com"))
 //Success(Success(Success(sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@2471fd20)))

  //so flatmap play very important role to unwrapped

  def inputStreamForURLWithFlatMap(url: String): Try[InputStream] = parseURL(url).flatMap { u =>
    Try(u.openConnection()).flatMap(conn => Try(conn.getInputStream))
  }

  println(inputStreamForURLWithFlatMap("http://www.google.com"))


  import scala.io.Source
  def getURLContent(url: String): Try[Iterator[String]] =
    for {
      url <- parseURL(url)
      connection <- Try(url.openConnection())
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines()

  import scala.util.Success
  import scala.util.Failure
  getURLContent("google.co.in") match {
    case Success(lines) => lines.foreach(println)
    case Failure(ex) => println(s"Problem rendering URL content: ${ex.getMessage}")
  }

  import java.net.MalformedURLException
  import java.io.FileNotFoundException
  val content = getURLContent("garbage") recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist")
    case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")
    case _ => Iterator("An unexpected error has occurred. We are so sorry!")
  }

  content.get.foreach(println)

}
