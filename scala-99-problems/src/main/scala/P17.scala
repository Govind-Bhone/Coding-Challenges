/**
  * Created by govind.bhone on 11/30/2016.
  */
object P17 extends App {
  // P17 (*) Split a list into two parts.
  //     The length of the first part is given.  Use a Tuple for your result.
  //
  //     Example:
  //     scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  //     res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))


  def splitList[T](index:Int,xs:List[T]):(List[T],List[T])=xs.splitAt(index)

  def splitList1[T](index:Int,xs:List[T]):(List[T],List[T])={
    (xs.take(index),xs.drop(index))
  }

  def splitList2[T](index:Int,xs:List[T]):(List[T],List[T])={
    def loop(n:Int,x1:List[T],x2:List[T]):(List[T],List[T])={
      (n,x2) match {
        case (0,h:: tail) =>(x1,x2)
        case (i,h::tail)=>loop(i-1,x1.:+(h),tail)
        case (_,Nil) => (x1,List.empty[T])
      }
    }
    loop(index,List.empty[T],xs)
  }

  // Simple recursion.
  def splitRecursive[A](n: Int, ls: List[A]): (List[A], List[A]) = (n, ls) match {
    case (_, Nil)       => (Nil, Nil)
    case (0, list)      => (Nil, list)
    case (n, h :: tail) => {
      val (pre, post) = splitRecursive(n - 1, tail)
      (h :: pre, post)
    }
  }

  // Tail recursive.
  def splitTailRecursive[A](n: Int, ls: List[A]): (List[A], List[A]) = {
    def splitR(curN: Int, curL: List[A], pre: List[A]): (List[A], List[A]) =
      (curN, curL) match {
        case (_, Nil)       => (pre.reverse, Nil)
        case (0, list)      => (pre.reverse, list)
        case (n, h :: tail) => splitR(n - 1, tail, h :: pre)
      }
    splitR(n, ls, Nil)
  }



  assert(splitTailRecursive(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))==(List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  assert(splitRecursive(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))==(List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  assert(splitList(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))==(List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  assert(splitList1(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))==(List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  assert(splitList2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))==(List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  assert(splitList2(3, List('a, 'b, 'c))==(List('a, 'b, 'c),List()))
  assert(splitList2(3, List('a, 'b))==(List('a, 'b),List()))
  assert(splitList(3, List('a, 'b))==(List('a, 'b),List()))
}
