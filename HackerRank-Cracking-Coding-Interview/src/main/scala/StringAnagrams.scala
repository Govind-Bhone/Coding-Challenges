/**
  * Created by govind.bhone on 11/11/2016.
  */

object StringAnagrams {


  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var a = sc.next();
    var b = sc.next();

    val totalLength = a.length + b.length

    println((a.diff(b) + b.diff(a)).length)

    //println(totalLength - a.filter(b.contains(_)).length-1)
    // println((a.filterNot(b.contains(_)) + b.filterNot(a.contains(_))).length)
  }
}

