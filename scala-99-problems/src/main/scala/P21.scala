/**
  * Created by govind.bhone on 12/2/2016.
  */
object P21 extends App {

  // P21 (*) Insert an element at a given position into a list.
  //     Example:
  //     scala> insertAt('new, 1, List('a, 'b, 'c, 'd))
  //     res0: List[Symbol] = List('a, 'new, 'b, 'c, 'd)

  def insertAt[T](newElement: T, index: Int, xs: List[T]): List[T] = {
    val (first, second) = xs.splitAt(index)
    first ::: (newElement :: second)
  }

  def insertAt3[T](elem : T, n : Int, ls: List[T]): List[T] =
    ls.take(n) ::: (elem :: ls.drop(n))


  def insertAt1[T](newElement: T, index: Int, xs: List[T]): List[T] = {
    if (xs.isEmpty || index < 0) return newElement :: xs

    xs.zipWithIndex.flatMap(f => if (f._2 == index) List(newElement, f._1) else List(f._1))
  }

  def insertAt2[T](newElement: T, index: Int, xs: List[T]): List[T] = {
    def loop(i: Int, xs: List[T]): List[T] = {
      (i, xs) match {
        case (_, Nil) => List(newElement)
        case (a, list) if (a==index || index < 0) => newElement :: list
        case ( a, h :: tail )=>h :: loop(i+1,tail)
      }
    }
    loop(0, xs)
  }


  assert(insertAt('new, 1, List('a, 'b, 'c, 'd)) == List('a, 'new, 'b, 'c, 'd))
  assert(insertAt1('new, 1, List('a, 'b, 'c, 'd)) == List('a, 'new, 'b, 'c, 'd))
  assert(insertAt2('new, 1, List('a, 'b, 'c, 'd)) == List('a, 'new, 'b, 'c, 'd))
  assert(insertAt3('new, 1, List('a, 'b, 'c, 'd)) == List('a, 'new, 'b, 'c, 'd))
  assert(insertAt('new, 1, List()) == List('new))
  assert(insertAt1('new, 1, List()) == List('new))
  assert(insertAt2('new, 1, List()) == List('new))
  assert(insertAt('new, -1, List()) == List('new))
  assert(insertAt1('new, -1, List()) == List('new))
  assert(insertAt2('new, -1, List()) == List('new))
  assert(insertAt('new, -1, List('a, 'b, 'c, 'd)) == List('new, 'a, 'b, 'c, 'd))
  assert(insertAt1('new, -1, List('a, 'b, 'c, 'd)) == List('new, 'a, 'b, 'c, 'd))
  assert(insertAt2('new, -1, List('a, 'b, 'c, 'd)) == List('new, 'a, 'b, 'c, 'd))
}
