/**
  * Created by govind.bhone on 11/20/2016.
  */
object AbstractTypesVsTypeParameterization extends App {

  //===================Abstract Type Implementation=======================
  trait Buffer {
    type T
    val element: T
  }

  abstract class SequenceBuffer extends Buffer {
    type U
    type T <: Seq[U]

    def length = element.length

    def head: U = element.head

    def tail: T = element.tail.asInstanceOf[T] //we can even use Seq[U] to avoid typecast


  }

  abstract class IntSeqBuffer extends SequenceBuffer {
    type U = Int

    override def toString = "[" + element.mkString(",") + "]"
  }

  val intBuffer = new IntSeqBuffer {
    override type T = List[Int]
    override val element: T = List(1, 2, 3, 4, 5)
  }
  println(intBuffer)
  println(intBuffer.length)
  println(intBuffer.head)
  println(intBuffer.tail)

  //=========================Type Parameterized Implementation=======================

  trait TypedBuffer[+T] {
    val element: T
  }

  abstract class SeqTypedBuffer[U, +T <: Seq[U]] extends TypedBuffer[T] {
    def length = element.length

    def head: U = element.head

    def tail: Seq[U] = element.tail
  }

  abstract class IntSeqTypedBuffer[U, +T <: Seq[U]] extends SeqTypedBuffer[U,T]{
    override def toString="["+element.mkString(",")+"]"
  }
  val intTypedBuffer = new IntSeqTypedBuffer[Int,List[Int]] {
    override val element= List(1, 2, 3, 4, 5)
  }

  println(intTypedBuffer)
  println(intTypedBuffer.length)
  println(intTypedBuffer.head)
  println(intTypedBuffer.tail)


}
