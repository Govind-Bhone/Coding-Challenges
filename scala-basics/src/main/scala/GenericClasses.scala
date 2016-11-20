/**
  * Created by govind.bhone on 11/19/2016.
  */
object GenericClasses extends App {

  class Stack[T] {
    var elms: List[T] = Nil

    def push(ele: T): Unit = elms = ele :: elms

    def pop(): T = {
      val head = top
      elms = elms.tail
      head
    }

    def top: T = elms.head
  }

  val stack = new Stack[Int]()
  stack.push(10)
  stack.push(20)
  println(stack.pop())
  stack.push(40)
  stack.push(50)
  println(stack.elms)
  println(stack.top)
}
