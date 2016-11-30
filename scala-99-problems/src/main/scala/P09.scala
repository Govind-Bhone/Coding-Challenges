/**
  * Created by govind.bhone on 11/29/2016.
  */
object P09 extends App {
  // P09 (**) Pack consecutive duplicates of list elements into sublists.
  //     If a list contains repeated elements they should be placed in separate
  //     sublists.
  //
  //     Example:
  //     scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //     res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))


  def packConsecutiveDuplicates[T](xs: List[T]): List[List[T]] = {
    xs match {
      case Nil => /*List(List())*/Nil
      case xh1 :: tail =>
        val (packed, next) = xs span {
          _ == xs.head
        }
       /* if (next == Nil) List(packed)
        else*/ packed :: packConsecutiveDuplicates(next)
    }
  }

  def packConsecutiveDuplicates1[T](xs: List[T]): List[List[T]] = {
    xs match {
      case Nil => List(List())
      case xh1 :: tail =>
        val duplicateList = xs.takeWhile(_ == xh1)
        val remainingList = xs.dropWhile(_ == xh1)
        if (remainingList == Nil) List(duplicateList)
        else duplicateList :: packConsecutiveDuplicates(remainingList)
    }
  }


  assert(packConsecutiveDuplicates(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))==List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)))
  assert(packConsecutiveDuplicates1(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))==List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)))

  assert(packConsecutiveDuplicates(List('a, 'a, 'a, 'a))==List(List('a, 'a, 'a, 'a)))

}
