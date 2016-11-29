import java.io.{File, PrintWriter}

import scala.io.Source

/**
  * Created by govind.bhone on 11/21/2016.
  */
object FileHandling extends App{

  val writer = new PrintWriter(new File("test.txt" ))

  writer.write("Hello Scala")
  writer.close()

  print("Please enter your input : " )
  val line = Console.readLine

  println("Thanks, you just typed: " + line)

  println("Following is the content read:" )

  Source.fromFile("test.txt" ).foreach {
    print
  }
}
