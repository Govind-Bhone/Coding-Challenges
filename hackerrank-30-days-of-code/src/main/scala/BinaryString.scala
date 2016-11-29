/**
  * Created by govind.bhone on 11/27/2016.
  */
object BinaryString {

  def OR(string: String, integer: Int) {
    val binaryInteger = Integer.toBinaryString(integer)

    for (b <- string.getBytes()) {
      val tmp = b | integer
      println(Integer.toBinaryString(b) + " OR " + Integer.toBinaryString(integer)
        + " = " + Integer.toBinaryString(tmp) + " = " + tmp)
    }
  }

  def main(args: Array[String]) {
    OR("HackerRank", 8675309)
  }
}


