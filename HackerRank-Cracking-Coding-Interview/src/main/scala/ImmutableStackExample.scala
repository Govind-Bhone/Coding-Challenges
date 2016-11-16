/**
  * Created by govind.bhone on 11/16/2016.
  */

trait Stack[+A]{
  def head :A
  def cons[B >:A](t: B): Stack[B]=new Cons(t,this)
  def update[B >: A](t: B, i: Int): Stack[B]
  def ++[U >:A](ys: Stack[U]): Stack[U]
  def toList: scala.collection.immutable.List[A]
  def tail : Stack[A]
  def isEmpty:Boolean
}

case object Empty extends Stack[Nothing] {
  def ++[U](ys: Stack[U]): Stack[U] = ys
  def update[U](t: U, i: Int) = throw new IndexOutOfBoundsException()
  def toList = scala.Nil
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException()
  def tail: Nothing = throw new NoSuchElementException()
}

case class Cons[+A](hd: A, tl: Stack[A]) extends Stack[A] {
  def isEmpty: Boolean = false
  def ++[U >: A](ys: Stack[U]): Stack[U] = Cons(head, tail ++ ys)
  def update[U >: A](t: U, i: Int): Stack[U] = if (i == 0) Cons(t, this) else Cons(head, tail.update(t, i-1))
  def toList = head :: tail.toList
  def head: A = hd
  def tail: Stack[A] = tl
}


object ImmutableStackExample extends App{
  var stack = Cons(1, Cons(2, Empty)).asInstanceOf[Stack[Int]]
  println(stack.head)
  println(stack.tail)
  stack=stack.cons(34)
  println(stack)
  stack=stack.++(Cons(45,Cons(56,Empty)))
  println(stack.toList)
}
