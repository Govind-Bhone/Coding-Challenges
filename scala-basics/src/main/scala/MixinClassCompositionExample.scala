import scala.collection.mutable

/**
  * Created by govind.bhone on 11/19/2016.
  */
abstract class AbsIterator {
  type T

  def hasNext: Boolean

  def next: T
}

trait RichIterator extends AbsIterator {
  def foreach(f: T => Unit) = while (hasNext) f(next)
  def map[B >: T <% Char](f:B=>B)={
  val buffer=new mutable.ArrayBuffer[Char]
    while(hasNext)buffer.+=(f(next))
    buffer
  }
}

class StringIterator(s: String) extends AbsIterator {
  type T = Char
  private var i = 0
  def hasNext=if(i<s.length)true else false
  def next ={val ch=s(i);i+=1;ch}
}

object MixinClassCompositionExample extends App {
  class Iter extends StringIterator("Hello World") with RichIterator
  val iter = new Iter
  //iter foreach println
  println(iter.map(x=>x.toUpper))
}
