import scala.collection.mutable

/**
  * Created by govind.bhone on 11/15/2016.
  */
trait MutableQueue {
  def isEmpty: Boolean
  def head: Int
  def tail: MutableQueue
  def size: Int

  var sizeValue = 1
  var mutableState = this

  def peek(): Int = {
    if (isEmpty) -1
    else mutableState.reverse.head
  }

  def enqueue(ele: Int): Unit = {
    mutableState = ele :: mutableState
    sizeValue = sizeValue + 1
  }

  def ::(item: Int): MutableQueue = NonEmptyNode(item, mutableState)

  def reverse: MutableQueue = {
    var result: MutableQueue = EmptyNode
    var these = this
    while (!these.isEmpty) {
      result = these.head :: result
      these = these.tail
    }
    result
  }

  def dequeue(): Int = {
    if (isEmpty) -1
    else {
      var elements = mutableState
      var tmp = elements.head :: EmptyNode
      while (!elements.tail.tail.isEmpty) {
        elements = elements.tail
        tmp = tmp.::(elements.head)
      }
      mutableState = tmp.reverse
      elements.head
    }
  }
}

case class NonEmptyNode(hd: Int, tl: MutableQueue) extends MutableQueue {
  def isEmpty = false
  def head = hd
  def tail = tl
  def size = sizeValue

  override def toString = {
    val builder = new mutable.ArrayBuffer[Int]()
    var elements = mutableState
    while (!elements.isEmpty) {
      builder.append(elements.head)
      elements = elements.tail
    }
    builder.result().mkString(" ")

  }
}

case object EmptyNode extends MutableQueue {
  def isEmpty = true
  def head = throw new NoSuchElementException("empty Queue")
  def tail = throw new NoSuchElementException("empty Queue")
  def size = sizeValue
  override def toString = ""
}

object MutableQueueTest extends App {
  val queue = 10 :: EmptyNode
  queue.enqueue(20)
  queue.enqueue(30)
  queue.enqueue(40)
  queue.enqueue(50)
  queue.enqueue(60)
  println(queue)
  queue.dequeue()
  queue.enqueue(70)
  queue.enqueue(80)
  queue.dequeue()
  println(queue.peek())
  println(queue)

}