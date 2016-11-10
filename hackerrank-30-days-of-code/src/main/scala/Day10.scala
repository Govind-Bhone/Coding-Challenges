/**
  * Created by govind.bhone on 11/10/2016.
  */
object Solution11 {

  def countHighestOneSequence(binaryString:String)={
    binaryString.split("0").sorted.last.length
  }
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var binaryString =""
    while( n >= 1 ){
      val r = n % 2
      binaryString +=r
      n = n /2
    }
    println(countHighestOneSequence(binaryString))
    //println(n.toBinaryString.split("0").sorted.last.length)

  }
}

