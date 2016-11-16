/**
  * Created by govind.bhone on 11/15/2016.
  */

trait List[+T] {
  def isEmpty: Boolean

  type U <: T

  def head: T

  def tail: List[T]

  def length: Int

  def drop(n: Int): List[T]

  def map[U](f: T => U): List[U]
}

case object Nil extends List[Nothing] {
  override def isEmpty = true

  def head: Nothing =
    throw new NoSuchElementException("head of empty list")

  def tail: List[Nothing] =
    throw new NoSuchElementException("tail of empty list")

  def length: Int = 0

  def drop(n: Int): List[Nothing] = this

  def map[U](f: Nothing => U): List[U] = this
}

final case class ::[T](private val hd: T, private val tl: List[T]) extends List[T] {
  def head = hd

  def tail = tl

  override def isEmpty: Boolean = false

  def length = if (isEmpty) 0 else 1 + tail.length

  def drop(n: Int): List[T] =
    if (isEmpty) Nil
    else if (n <= 0) this
    else tail.drop(n - 1)

  def map[U](f: T => U): List[U] =
    if (isEmpty) Nil
    else ::(f(head), tail.map(f))
}

object LinkedListTest extends App {
  var list: List[Int] = ::[Int](10, ::(20, Nil))
  println(list.head)
  println(list.isEmpty)
  println(list.length)
  list = list.map(x => x + 2)
  println(list.drop(1))

}
