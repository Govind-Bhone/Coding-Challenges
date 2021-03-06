/**
  * Created by govind.bhone on 11/30/2016.
  */
object P18 extends App {
  // P18 (**) Extract a slice from a list.
  //     Given two indices, I and K, the slice is the list containing the elements
  //     from and including the Ith element up to but not including the Kth
  //     element of the original list.  Start counting the elements with 0.
  //
  //     Example:
  //     scala> slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  //     res0: List[Symbol] = List('d, 'e, 'f, 'g)

  def sliceList[T](start: Int, end: Int, xs: List[T]): List[T] = xs.slice(start, end)

  def sliceList1[T](start: Int, end: Int, xs: List[T]): List[T] = xs.drop(start).take(end - start)


  def sliceList2[T](start: Int, end: Int, xs: List[T]): List[T] = {
    def loop(pointer: Int, result: List[T], xs: List[T]): List[T] = {
      (pointer, xs) match {
        case (_, Nil) => Nil
        case (st, xh :: tail) if (st >=end ) =>result
        case (x,xh::tail)=>loop(x+1,if(x < end && x >= start)result.:+(xh) else result,tail)
      }
    }
    loop(0,List.empty[T],xs)
  }

  // Simple recursive.
  def sliceRecursive[A](start: Int, end: Int, ls: List[A]): List[A] =
    (start, end, ls) match {
      case (_, _, Nil)                 => Nil
      case (_, e, _)         if e <= 0 => Nil
      case (s, e, h :: tail) if s <= 0 => h :: sliceRecursive(0, e - 1, tail)
      case (s, e, h :: tail)           => sliceRecursive(s - 1, e - 1, tail)
    }

  // Tail recursive, using pattern matching.
  def sliceTailRecursive[A](start: Int, end: Int, ls: List[A]): List[A] = {
    def sliceR(count: Int, curList: List[A], result: List[A]): List[A] =
      (count, curList) match {
        case (_, Nil)                     => result.reverse
        case (c, h :: tail) if end <= c   => result.reverse
        case (c, h :: tail) if start <= c => sliceR(c + 1, tail, h :: result)
        case (c, _ :: tail)               => sliceR(c + 1, tail, result)
      }
    sliceR(0, ls, Nil)
  }

  // Since several of the patterns are similar, we can condense the tail recursive
  // solution a little.
  def sliceTailRecursive2[A](start: Int, end: Int, ls: List[A]): List[A] = {
    def sliceR(count: Int, curList: List[A], result: List[A]): List[A] = {
      if (curList.isEmpty || count >= end) result.reverse
      else sliceR(count + 1, curList.tail,
        if (count >= start) curList.head :: result
        else result)
    }
    sliceR(0, ls, Nil)
  }

  // Functional.
  def sliceFunctional[A](s: Int, e: Int, ls: List[A]): List[A] =
    ls drop s take (e - (s max 0))

  assert(sliceList(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g))
  assert(sliceList1(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g))
  assert(sliceRecursive(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g))
  assert(sliceTailRecursive(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g))
  assert(sliceTailRecursive2(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g))
  assert(sliceFunctional(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g))
}
