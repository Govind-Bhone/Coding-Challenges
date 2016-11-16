/**
  * Created by govind.bhone on 11/16/2016.
  */
abstract class MutableList {
  def head: Int
  var mutableState: MutableList = this
  def tail: MutableList
  def isEmpty: Boolean
  def length: Int
  def ::(item: Int): MutableList = MutableListImpl(item, this)

  def reverse: MutableList = {
    var result: MutableList = MutableListNil
    var these = mutableState
    while (!these.isEmpty) {
      result = these.head :: result
      these = these.tail
    }
    mutableState = result
    mutableState
  }

  def :+(item: Int): MutableList = {
    var result: MutableList = item :: MutableListNil
    var these = mutableState
    while (!these.isEmpty) {
      result = these.head :: result
      these = these.tail
    }
    mutableState = result
    mutableState
  }

  def drop(n: Int): Unit = {
    var xs = mutableState
    var i = 0
    while ((!xs.isEmpty) && i < n) {
      xs = xs.tail
      i = i + 1
    }
    mutableState = xs
  }

  def dropWhile(p: Int => Boolean): Unit = {
    var xs = mutableState
    while (!xs.isEmpty && p(xs.head)) {
      xs = xs.tail
    }
    mutableState = xs
  }

  def take(n: Int): MutableList = {
    var result: MutableList = MutableListNil
    def loop(i: Int, xs: MutableList): MutableList = {
      if (xs.isEmpty || i >= n) {
        mutableState = xs
        result
      }
      else {
        result = result.::(xs.head)
        loop(i + 1, xs.tail)
      }
    }
    loop(1, mutableState).reverse
  }

  def map(f: Int => Int): MutableList = {
    if (isEmpty) MutableListNil
    else {
      mutableState = f(head) :: tail.map(f)
      mutableState
    }
  }

  def takeWhile(p: Int => Boolean): MutableList = {
    var result: MutableList = MutableListNil
    def loop(xs: MutableList): MutableList =
      if (xs.isEmpty || !p(xs.head)) {
        mutableState = xs
        result
      }
      else {
        result = result.::(xs.head)
        loop(xs.tail)
      }
    loop(mutableState).reverse
  }

  def update(t: Int, i: Int): MutableList = {
    if (i == 0) {
      mutableState = MutableListImpl(t, mutableState.tail)
    }
    else {
      mutableState = MutableListImpl(mutableState.head, mutableState.tail.update(t, i - 1))
    }
    mutableState
  }
}

case class MutableListImpl(val head: Int, val tail: MutableList) extends MutableList {
  def isEmpty = false
  def length: Int = 1 + tail.length

  override def toString: String = {
    val builder = new scala.collection.mutable.ArrayBuffer[Int]()
    var elements = mutableState
    while (!elements.isEmpty) {
      builder.append(elements.head)
      elements = elements.tail
    }
    builder.result().mkString(" ")
  }
}

case object MutableListNil extends MutableList {
  def head: Nothing = throw new Exception("head of empty list")
  def tail: MutableList = throw new Exception("tail of empty list")
  def isEmpty = true
  def length = 0
  override def toString = ""
}

object MutableList {
  def apply(items: Int*): MutableList = {
    var list: MutableList = MutableListNil
    for (idx <- 0 until items.length)
      list = list.::(items(idx))
    list
  }
}


object MutableListTest extends App {
  val list = MutableList(4, 2, 4, 6, 10, 12, 14, 56, 78, 90, 1, 2, 3, 60, 89, 100, 90)
  println("Initial List : " + list)
  list.map(x => x + 2)
  println("Mapped List : " + list)
  println("Dropped Element : " + list.drop(3))
  println("Remaining Element : " + list)
  println("Dropped Element by v2 : " + list.dropWhile(x => x % 2 == 0))
  println("Remaining Element after v2 drop : " + list)
  println("Reverse List : " + list.reverse)
  println("Returned Reverse List : " + list)
  println("Element taken : " + list.take(3))
  println("Element Remaining : " + list)
  println("Element taken : " + list.takeWhile(x => x % 2 == 0))
  println("Element Remaining : " + list)
  println("Updated List : " + list.update(10000, 0))
  println("Final List : " + list)
}
