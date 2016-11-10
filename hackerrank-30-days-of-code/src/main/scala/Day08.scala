/**
  * Created by govind.bhone on 11/10/2016.
  */
object Solution9 {

  def main(args: Array[String]) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
    val scanner = new java.util.Scanner(System.in)
    val noOfRecords = scanner.nextInt()
    val dict = new scala.collection.mutable.HashMap[String,String]()
    for(i<-0 until noOfRecords ){
      val name =scanner.next
      val number=scanner.next
      dict.put(name,number)
    }

    while(scanner.hasNext){
      val name=scanner.next
      if(dict.contains(name))println(s"${name}=${dict(name)}")
      else println("Not found")
    }

  }
}