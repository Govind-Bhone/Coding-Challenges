/**
  * Created by govind.bhone on 11/29/2016.
  */
object P08 extends App {

  //     Eliminate consecutive duplicates of list elements.
  //     If a list contains repeated elements they should be replaced with a
  //     single copy of the element.  The order of the elements should not be
  //     changed.
  //
  //     Example:
  //     scala> removeConsecutiveDuplicates(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //     res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)

  def removeConsecutiveDuplicates[T](xs: List[T]): List[T] = {
    xs match {
      case xh1 :: xh2 :: Nil => if (xh1 == xh2) xh1 :: Nil else xh1 :: xh2 :: Nil
      case xh1 :: xh2 :: tail => if (xh1 == xh2) removeConsecutiveDuplicates(xh2 :: tail) else xh1 :: removeConsecutiveDuplicates(xh2 :: tail)
    }
  }

  println(removeConsecutiveDuplicates(List(1, 1, 1, 2, 2, 3, 4, 5)))
  println(removeConsecutiveDuplicates(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))


  //  recursive implementation using dropWhile fun .
  def compressRecursive[A](ls: List[A]): List[A] = ls match {
    case Nil => Nil
    case h :: tail => h :: compressRecursive(tail.dropWhile(_ == h))
  }

  println(compressRecursive(List(1, 1, 1, 2, 2, 3, 4, 5)))
  println(compressRecursive(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  // Tail recursive.
  def compressTailRecursive[A](ls: List[A]): List[A] = {
    def compressR(result: List[A], curList: List[A]): List[A] = curList match {
      case h :: tail => compressR(h :: result, tail.dropWhile(_ == h))
      case Nil => result.reverse
    }
    compressR(Nil, ls)
  }

  println(compressTailRecursive(List(1, 1, 1, 2, 2, 3, 4, 5)))
  println(compressTailRecursive(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  // Functional.
  def compressFunctionalRight[A](ls: List[A]): List[A] =
    ls.foldRight(List[A]()) { (h, acc) =>
      if (acc.isEmpty || acc.head != h) h :: acc
      else acc
    }

  println(compressFunctionalRight(List(1, 1, 1, 2, 2, 3, 4, 5)))
  println(compressFunctionalRight(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  def compressFunctionalLeft[A](ls: List[A]): List[A] =
    ls.foldLeft(List[A]()) { (acc,h) =>
      if (acc.isEmpty || acc.head != h) h :: acc
      else acc
    }.reverse

  println(compressFunctionalLeft(List(1, 1, 1, 2, 2, 3, 4, 5)))
  println(compressFunctionalLeft(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))


}
