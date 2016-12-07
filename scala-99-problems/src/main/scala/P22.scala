import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
  * Created by govind.bhone on 12/2/2016.
  */
object P22 extends App {
  // P22 (*) Create a list containing all integers within a given range.
  //     Example:
  //     scala> range(4, 9)
  //     res0: List[Int] = List(4, 5, 6, 7, 8, 9)

  def range(startElement: Int, endElement: Int): List[Int] = (startElement to endElement).toList

  assert(range(4, 9) == List(4, 5, 6, 7, 8, 9))

  def range1(startElement: Int, endElement: Int): List[Int] = (for (i <- startElement to endElement) yield i).toList

  assert(range1(4, 9) == List(4, 5, 6, 7, 8, 9))

  def range2(startElement: Int, endElement: Int): List[Int] = {
    val listBuffer = new ListBuffer[Int]
    for (i <- startElement to endElement) listBuffer.+=(i)
    listBuffer.result()
  }

  assert(range2(4, 9) == List(4, 5, 6, 7, 8, 9))

  def range3(startElement: Int, endElement: Int): List[Int] = {
    val arrayBuffer = new ArrayBuffer[Int]
    for (i <- startElement to endElement) arrayBuffer.+=(i)
    arrayBuffer.result().toList
  }

  assert(range3(4, 9) == List(4, 5, 6, 7, 8, 9))

  def range4(startElement: Int, endElement: Int): List[Int] = List.range(startElement, endElement + 1, 1)

  assert(range4(4, 9) == List(4, 5, 6, 7, 8, 9))

  // Recursive.
  def rangeRecursive(start: Int, end: Int): List[Int] =
    if (end < start) Nil
    else start :: rangeRecursive(start + 1, end)

  // Tail recursive.
  def rangeTailRecursive(start: Int, end: Int): List[Int] = {
    def rangeR(end: Int, result: List[Int]): List[Int] = {
      if (end < start) result
      else rangeR(end - 1, end :: result)
    }
    rangeR(end, Nil)
  }

  assert(rangeRecursive(4, 9) == List(4, 5, 6, 7, 8, 9))
  assert(rangeTailRecursive(4, 9) == List(4, 5, 6, 7, 8, 9))


  // The classic functional approach would be to use `unfoldr`, which Scala
  // doesn't have.  So we'll write one and then use it.
  def unfoldRight[A, B](s: B)(f: B => Option[(A, B)]): List[A] =
    f(s) match {
      case None => Nil
      case Some((r, n)) => r :: unfoldRight(n)(f)
    }

  def rangeFunctional(start: Int, end: Int): List[Int] =
    unfoldRight(start) { n =>
      if (n > end) None
      else Some((n, n + 1))
    }

  assert(rangeFunctional(4, 9) == List(4, 5, 6, 7, 8, 9))


}
