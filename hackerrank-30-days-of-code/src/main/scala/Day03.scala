/**
  * Created by govind.bhone on 11/10/2016.
  */
object Solution4 {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var N = sc.nextInt();

    N match {
      case n if n % 2 !=0 => println("Weird")
      case n if n >=2 && n <= 5 => println("Not Weird")
      case n if n >=6 && n <=20 => println("Weird")
      case n if n > 20 => println("Not Weird")
    }

  }
}
