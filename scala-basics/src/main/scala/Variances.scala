/**
  * Created by govind.bhone on 11/19/2016.
  */
object Variances extends App{

  class STackV[+A]{
    def push[B>:A](ele:B):STackV[B]=new STackV[B]{
      override def pop:STackV[B] =STackV.this
      override def top:B=ele
      override def toString() = ele.toString() + " " +
        STackV.this.toString()
    }

    def top :A=sys.error("top of empty stack")
    def pop:STackV[A]=sys.error("no element on stack")

    override def toString="empty stack"
  }

  var s: STackV[Any] = new STackV().push("hello");
  s = s.push(new Object())
  s = s.push(7)
  println(s)

}