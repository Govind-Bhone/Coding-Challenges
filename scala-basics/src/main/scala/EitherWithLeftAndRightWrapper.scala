/**
  * Created by govind.bhone on 11/20/2016.
  */
object EitherWithLeftAndRightWrapper extends App {

  import scala.io.Source
  import java.net.URL

  def getContent(url: URL): Either[String, Source] =
    if (url.getHost.contains("google"))
      Left("Requested URL is blocked for the good of the people!")
    else
      Right(Source.fromURL(url))


  getContent(new URL("http://google.com")) match {
    case Left(msg) => println(msg)
    case Right(source) => source.getLines.foreach(println)
  }

  //Either is unbiased so you need to call right or left method on it

  val content: Either[String, Iterator[String]] =
    getContent(new URL("http://danielwestheide.com")).right.map(_.getLines())

  println(content)
  // content is a Right containing the lines from the Source returned by getContent
  val moreContent: Either[String, Iterator[String]] =
    getContent(new URL("http://google.com")).right.map(_.getLines)
  // moreContent is a Left, as already returned by getContent
  println(moreContent)
}
