import java.io.File

import scala.io.Source

/**
  * Created by govind.bhone on 5/8/2017.
  */

case class Info(country: String, state: String, population: Long)

object Main extends App {

  val lines = Source
    .fromFile(new File("""C:\\Users\\govind.bhone\\IdeaProjects\\scala-advanced\\src\\main\\resources\\input.txt"""))
    .getLines()

  val objects = lines.map {
    f =>
      val fields = f.split(",")
      Info(fields(0), fields(1), fields(2).toLong)
  }.toList

  val formattedOutput = objects
    .groupBy(_.country)
    .map(f => List(f._1, f._2.map(_.state).mkString(","), f._2.map(_.population).sum).mkString(","))

  formattedOutput.foreach(println)
}
