/**
  * Created by govind.bhone on 11/19/2016.
  */
object CurryingFunctions extends App {

  def filter(xs: List[Int], predicate: Int => Boolean): List[Int] = {
    if (xs.isEmpty) xs
    else if (predicate(xs.head)) xs.head :: filter(xs.tail, predicate)
    else filter(xs.tail, predicate)
  }

  val modFun:Int=>Int=>Boolean=(x:Int)=>(y:Int)=> y % x==0
  val modFun2=modFun(2)

  val nums=List(1,2,3,5,6,7,8,9,10)
  println(filter(nums,modFun(2)))
  println(filter(nums,modFun2))

}
