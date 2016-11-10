/**
  * Created by govind.bhone on 11/10/2016.
  */
object Solution6 {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    (1 to 10).map(i=>println(s"${n} x ${i} = ${n * i}"))
  }
}

