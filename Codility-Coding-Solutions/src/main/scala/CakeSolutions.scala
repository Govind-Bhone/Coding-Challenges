/**
  * Created by govind.bhone on 3/8/2017.
  */

object StringCompression {

  def compressIntoPairs(s: List[Char], acc: List[(Char, Int)], ch: Char, counter: Int): List[(Char, Int)] = {
    if(s.isEmpty)  acc.:+((ch,counter))
    else {
      if(s.head.equals(ch)){
        compressIntoPairs(s.tail,acc,s.head, counter=counter+1)
      }else{
        compressIntoPairs(s.tail,acc.:+((ch,counter)),s.head,1)
      }
    }
  }

  // "aaabccdeffff" => "a3bc2def4"
  def compress(s: String) = {
   compressIntoPairs(s.tail.toList,List.empty,s.head,1).map {
     case (char, 1) => s"$char"
     case (char ,count ) =>s"$char$count"
   }.mkString("")
  }

  def main(args: Array[String]): Unit = {
    val s = "aaabccdeffff"
    println(compress(s))
  }

}


