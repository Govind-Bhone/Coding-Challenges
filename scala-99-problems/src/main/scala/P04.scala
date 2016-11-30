/**
  * Created by govind.bhone on 11/24/2016.
  */
object P04 extends App{

  def length[T](xs:List[T]):Int={
    xs match {
      case Nil => 0
      case _ :: tail => 1 + length(tail)
    }
  }

  def length1[T](xs:List[T]):Int=xs.length

  def length2[T](xs:List[T]):Int={
    def loop(acc:Int,xs:List[T]):Int={
      xs match {
        case Nil => acc
        case _ :: tail => loop(acc+1,tail)
      }
    }
    loop(0,xs)
  }

  def length3[T](xs:List[T])=xs.foldLeft(0){(c,_)=>c+1}

  assert(length(List(1,2,3,4,5,6,7))==7)
  assert(length1(List(1,2,3,4,5,6,7))==7)
  assert(length2(List(1,2,3,4,5,6,7))==7)
  assert(length3(List(1,2,3,4,5,6,7))==7)

}
