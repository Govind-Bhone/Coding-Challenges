/**
  * Created by govind.bhone on 11/16/2016.
  */

trait Queue[+A] {
  def head: A
  def enqueue[B >: A](t: B): Queue[B] = new NonEmptyQueue(t, this)

  def reverse: Queue[A] = {
    var result: Queue[A] = EmptyQueue
    var these = this
    while (!these.isEmpty) {
      result = result.enqueue(these.head)
      these = these.tail
    }
    result
  }

  def dequeue(): Queue[A] = {
    if (isEmpty) this
    else {
      var these = this
      var that = EmptyQueue.enqueue(these.head)
      while (!these.tail.tail.isEmpty) {
        these = these.tail
        that = that.enqueue(these.head)
      }
      that.reverse
    }
  }

  def update[B >: A](t: B, i: Int): Queue[B]
  def ++[U >: A](ys: Queue[U]): Queue[U]
  def toList: scala.collection.immutable.List[A]
  def tail: Queue[A]
  def isEmpty: Boolean
}

case object EmptyQueue extends Queue[Nothing] {
  def ++[U](ys: Queue[U]): Queue[U] = ys
  def update[U](t: U, i: Int) = throw new IndexOutOfBoundsException()
  def toList = scala.Nil
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException()
  def tail: Nothing = throw new NoSuchElementException()
}

case class NonEmptyQueue[+A](hd: A, tl: Queue[A]) extends Queue[A] {
  def isEmpty: Boolean = false
  def ++[U >: A](ys: Queue[U]): Queue[U] = NonEmptyQueue(head, tail ++ ys)
  def update[U >: A](t: U, i: Int): Queue[U] = if (i == 0) NonEmptyQueue(t, this) else NonEmptyQueue(head, tail.update(t, i - 1))
  def toList = head :: tail.toList
  def head: A = hd
  def tail: Queue[A] = tl
}


object ImmutableQueueExample extends App {
  var queue = NonEmptyQueue(2, NonEmptyQueue(1, EmptyQueue)).asInstanceOf[Queue[Int]]
  println("Initial Queue is : "+queue)
  println("Head of Queue : "+queue.head)
  println("Tail of Queue : "+queue.tail)
  queue = queue.enqueue(3)
  println("Queue  after Enqueue : "+queue)
  queue = queue.dequeue()
  println("Queue After Dequeue :"+queue)
  queue = queue.++(NonEmptyQueue(45, NonEmptyQueue(56, EmptyQueue)))
  println("Queue after seq append : "+queue)
  println(queue.toList)
}
