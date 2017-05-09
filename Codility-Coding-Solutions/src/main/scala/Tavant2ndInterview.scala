object Main2 extends App {

  //========================PRIME NUMBER ====================================
  def prime(list: List[Int]) = {
    for (n <- list) {
      var isPrime = true
      scala.util.control.Breaks.breakable {
        for (i <- 2 until n) {
          if (n % i == 0) {
            isPrime = false
            scala.util.control.Breaks.break();
          }
        }
      }
      if (isPrime) {
        println(s"${n} is prime")
      } else {
        println(s"${n} is not prime")
      }
    }
  }

  val list = List(12, 45, 11, 78)
  prime(list)
  
    //================ANOTHER APPROACH FOR USING FUNCTIONAL WAY
  val res=(for{
    l<-list
  } yield if(!(2 to l-1).exists(l%_==0)) Some(l) else None).filter(_!=None).map(_.get)
  println("Second way is :"+res)

  // ============FIBONACCI SERIES(1, 1, 2, 3, 5, 8) =================
  def fibonacci(n: Int) = {
    def loop(first: Int, second: Int, result: List[Int], count: Int): List[Int] = {
      count match {
        case i if i == 0 =>
          result
        case _ =>
          val next=first+second
          loop(second,next, result.:+(next), count - 1)
      }
    }

    loop(1, 1, List(1, 1), n)
  }

  println(fibonacci(6))


  //==========================SUM OF NUMBERS FROM LIST=============================
  println(List(1, 2, 3).sum)
  println(List(1, 2, 3).reduce(_ + _))
  println(List(1, 2, 3).foldLeft(0) {_ + _})
  def sum = (acc: Int, i: Int) => acc + i
  println(List(1, 2, 3).reduce(sum))

}
