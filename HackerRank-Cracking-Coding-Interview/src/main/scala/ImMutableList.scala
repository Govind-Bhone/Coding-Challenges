/**
  * Created by govind.bhone on 11/15/2016.
  */

abstract class NewList[+A] {
  def head: A

  def tail: NewList[A]

  def isEmpty: Boolean

  def length: Int

  def ::[B >: A, A](item: A): NewList[A] = NewListImpl[A](item, this.asInstanceOf[NewList[A]])

  def reverse: NewList[A] = {
    var result: NewList[A] = NewListNil
    var these = this
    while (!these.isEmpty) {
      result = these.head :: result
      these = these.tail
    }
    result
  }

  def :+[B >: A](item: B): NewList[B] = {
    var result: NewList[B] = item :: NewListNil
    var these = this
    while (!these.isEmpty) {
      result = these.head :: result
      these = these.tail
    }
    result
  }

  def map[U](f: A => U): NewList[U] = {
    if (isEmpty) NewListNil.asInstanceOf[NewList[U]]
    else
      f(head) :: tail.map(f)
  }

  def drop(n: Int): NewList[A] = {
    if (n <= 0) this
    else
      this.tail.drop(n - 1)
  }

  def dropWhile(p: (A) => Boolean): NewList[A] = {
    def loop(xs: NewList[A]): NewList[A] =
      if (xs.isEmpty || !p(xs.head)) xs
      else loop(xs.tail)

    loop(this)
  }

  def take(n: Int): NewList[A] = {
    var result: NewList[A] = NewListNil
    var these = this
    var i = n
    while (i >= 0) {
      if (these.isEmpty) return result
      result = these.head :: result
      these = these.tail
      i = i - 1
    }
    result.reverse
  }

  def takeWhile(p: (A) => Boolean): NewList[A] = {
    var result: NewList[A] = NewListNil
    def loop(xs: NewList[A]): NewList[A] =
      if (xs.isEmpty || !p(xs.head)) {
        result
      }
      else {
        result = result.::(xs.head)
        loop(xs.tail)
      }

    loop(this)
  }

  def foreach[B >: A](f: A => B): Unit = {
    var these = this
    while (!these.isEmpty) {
      f(these.head)
      these = these.tail
    }
  }

  def foldRight[B](z: B)(f: (B, A) => B): B = {
    this.reverse.foldLeft(z)(f)
  }

  def foldLeft[B](z: B)(f: (B, A) => B): B = {
    var accumulator = z
    var these = this
    while (!these.isEmpty) {
      accumulator = f(accumulator, these.head)
      these = these.tail
    }
    accumulator
  }

  def :::[B >: A, A](other: NewList[A]): NewList[A] = {
    val op = other match {
      case NewListNil => this.asInstanceOf[NewList[A]]
      case NewListImpl(ele, NewListNil) => ele :: this
      case NewListImpl(ele, p@_) => this.::(ele) ++ p
    }
    op
  }

  def ++[B >: A, A](other: NewList[A]): NewList[A] = {
    val op = other match {
      case NewListNil => this.asInstanceOf[NewList[A]]
      case NewListImpl(ele, NewListNil) => ele :: this
      case NewListImpl(ele, p@_) => this.::(ele) ++ p //NewListImpl(this.head, this.tail ++ other).asInstanceOf[NewList[A]]
    }
    op.reverse
  }

  def update[B >: A](t: B, i: Int): NewList[B] =
    if (i == 0) NewListImpl(t, this.tail)
    else NewListImpl(head, tail.update(t, i - 1))
}

case class NewListImpl[A](val head: A, val tail: NewList[A]) extends NewList[A] {
  def isEmpty = false

  def length: Int = 1 + tail.length

  override def toString: String = head + " " + tail
}

case object NewListNil extends NewList[Nothing] {
  def head: Nothing = throw new Exception("head of empty list")

  def tail: NewList[Nothing] = throw new Exception("tail of empty list")

  def isEmpty = true

  def length = 0

  override def toString = ""
}

object NewList {
  def apply[A](items: A*): NewList[A] = {
    var list: NewList[A] = NewListNil.asInstanceOf[NewList[A]]
    for (idx <- 0 until items.length)
      list = list.::(items(idx))
    list
  }
}


object MyList extends App {
  val list = NewList(4, 2, 4, 6, 45, 34, 234)
  println(list)
  println(list.map(x => x + 2))
  println(list.drop(3))
  println(list.dropWhile(x => x % 2 == 0))
  println(list.++(NewList(100, 200, 300)))
  println(list.:::(NewList(100, 200, 300)))
  println(list.reverse)
  println(list.:+(34))
  println(list.foldRight(0)(_ + _))
  list.foreach(x => println(x + 2))
  println(list.take(3))
  println(list.takeWhile(x => x % 2 == 0))
  println(list.update(10000, 0))


}