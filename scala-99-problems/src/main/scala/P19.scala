/**
  * Created by govind.bhone on 12/1/2016.
  */
object P19 extends App {
  // P19 (**) Rotate a list N places to the left.
  //     Examples:
  //     scala> rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  //     res0: List[Symbol] = List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
  //
  //     scala> rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  //     res1: List[Symbol] = List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)


  def rotateLeft2[T](n: Int, xs: List[T]): List[T] = {
    (n, xs) match {
      case (_, Nil) => Nil
      case (0, xh :: tail) => xs
      case (i, xh :: tail) if (i < 0) => rotateLeft2(i + xs.length, tail.:+(xh))
      case (i, xh :: tail) => rotateLeft2(i - 1, tail.:+(xh))
    }
  }

  def rotate3[A](n: Int, ls: List[A]): List[A] = {
    val nBounded = if (ls.isEmpty) 0 else n % ls.length
    if (nBounded < 0) rotate3(nBounded + ls.length, ls)
    else (ls drop nBounded) ::: (ls take nBounded)
  }


  def rotateLeft[T](n: Int, xs: List[T]): List[T] = {
    val nBounded = if (xs.isEmpty) 0 else n % xs.length
    if (nBounded < 0) rotate3(nBounded + xs.length, xs)
    else {
      val (first, second) = xs.splitAt(nBounded)
      second ::: first
    }
  }

  assert(rotateLeft2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c))
  assert(rotateLeft(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c))
  assert(rotate3(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c))

  assert(rotate3(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i))
  assert(rotateLeft2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c))
  assert(rotateLeft(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c))


}
