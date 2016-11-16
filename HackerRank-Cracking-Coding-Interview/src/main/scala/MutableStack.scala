import scala.collection.mutable

/**
  * Created by govind.bhone on 11/15/2016.
  */
trait NewStack {

  val stackLimit = 5
  var mutableState: NewStack = this
  var sizeState = 1

  def isEmpty: Boolean

  def push(t: Int) = {
    if (size == stackLimit) {
      println("Reached the limit ,can't push ")
      this
    } else {
      sizeState = sizeState + 1
      mutableState = NewStackImpl(t, mutableState)
    }
  }

  def pop(): Int = {
    if (mutableState.isEmpty) {
      println("Stack Underflow")
      -1
    } else {
      val poppedElement = mutableState.head
      mutableState = mutableState.tail
      sizeState = sizeState - 1
      poppedElement
    }
  }

  def head: Int
  def size: Int
  def tail: NewStack
}

case class NewStackImpl(val head: Int, val tail: NewStack) extends NewStack {
  def isEmpty = false
  def size: Int = sizeState

  override def toString: String = {
    val builder = new mutable.ArrayBuffer[Int]()
    var elements = mutableState
    while (!elements.isEmpty) {
      builder.append(elements.head)
      elements = elements.tail
    }
    builder.result().mkString(" ")
  }
}

case object NewStackNil extends NewStack {
  def head: Nothing = throw new Exception("Empty Stack")
  def tail: NewStack = throw new Exception("Empty Stack")
  def isEmpty = true
  def size = sizeState
  override def toString = ""
}

object StackTest extends App {
  var stack: NewStack = NewStackImpl(10, NewStackNil)
  stack.push(20)
  stack.push(30)
  stack.push(40)
  stack.push(50)
  stack.push(60)
  println(stack)
  stack.pop()
  println(stack)
  stack.pop()
  println(stack)


}