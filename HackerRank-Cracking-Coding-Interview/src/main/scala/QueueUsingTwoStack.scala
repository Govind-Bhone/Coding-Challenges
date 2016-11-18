/**
  * Created by govind.bhone on 11/17/2016.
  *
*/
trait StackQueue[T] {
  val stackWithNewestOnTop = new scala.collection.mutable.Stack[T]()
  val stackWithOldestOnTop=new scala.collection.mutable.Stack[T]()
  def enqueue(ele:T):Unit
  def dequeue():T
  def peek():T
}

class MyQueue[T] extends StackQueue[T]{
  def enqueue(ele:T): Unit ={
    stackWithNewestOnTop.push(ele)
  }

  private def shiftStacks(): Unit ={
    if(stackWithOldestOnTop.isEmpty){
      while(!stackWithNewestOnTop.isEmpty){
        stackWithOldestOnTop.push(stackWithNewestOnTop.pop)
      }
    }
  }

  def dequeue(): T ={
    shiftStacks
    return stackWithOldestOnTop.pop()
  }

  def peek(): T ={
    shiftStacks
    return stackWithOldestOnTop.top
  }
}

object QueueUsingTwoStack extends App {
  val queue = new MyQueue[Int]()
  val scan = new java.util.Scanner(System.in)
  val n = scan.nextInt()

  for (i <- 0 to n) {
    val operation = scan.nextInt()
    if (operation == 1) {
      // enqueue
      queue.enqueue(scan.nextInt())
    } else if (operation == 2) {
      // dequeue
      queue.dequeue()
    } else if (operation == 3) {
      // print/peek
      println(queue.peek())
    }
  }
  scan.close()
}
