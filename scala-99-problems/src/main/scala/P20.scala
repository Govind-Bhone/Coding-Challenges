/**
  * Created by govind.bhone on 12/1/2016.
  */
object P20 extends App {
  // P20 (*) Remove the Kth element from a list.
  //     Return the list and the removed element in a Tuple.  Elements are
  //     numbered from 0.
  //
  //     Example:
  //     scala> removeAt(1, List('a, 'b, 'c, 'd))
  //     res0: (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

  def removeAt1[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
    case (Nil, _) if n < 0 => throw new NoSuchElementException
    case (pre, e :: post) => (pre ::: post, e)
    case (pre, Nil) => throw new NoSuchElementException
  }

  // Alternate, with fewer builtins.
  def removeAt2[A](n: Int, ls: List[A]): (List[A], A) =
    if (n < 0) throw new NoSuchElementException
    else (n, ls) match {
      case (_, Nil) => throw new NoSuchElementException
      case (0, h :: tail) => (tail, h)
      case (_, h :: tail) => {
        val (t, e) = removeAt(n - 1,tail)
        (h :: t, e)
      }
    }

  def removeAt[T](n: Int, xs: List[T]): (List[T], T) = (xs.zipWithIndex.filter(ele => ele._2 != n).map(f => f._1), xs(n))

  assert(removeAt(1, List('a, 'b, 'c, 'd)) ==(List('a, 'c, 'd), 'b))
  assert(removeAt2(1, List('a, 'b, 'c, 'd)) ==(List('a, 'c, 'd), 'b))
  assert(removeAt1(1, List('a, 'b, 'c, 'd)) ==(List('a, 'c, 'd), 'b))
}
