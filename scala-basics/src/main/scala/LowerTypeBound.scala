/**
  * Created by govind.bhone on 11/19/2016.
  */
object LowerTypeBound extends App {

  case class ListNode[+T](h: T, t: ListNode[T]) {
    def head: T = h
    def tail: ListNode[T] = t
    def prepend[B>:T](ele: B): ListNode[B] = ListNode(ele, this)
  }

  val empty: ListNode[Null] = ListNode(null, null)
  val strList: ListNode[String] = empty.prepend("hello")
    .prepend("world")
  val anyList: ListNode[Any] = strList.prepend(12345)

  println(anyList)
}
