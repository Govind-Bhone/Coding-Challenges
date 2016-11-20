/**
  * Created by govind.bhone on 11/20/2016.
  */
object InfixVsPrefixOperators extends App{

  //Any method which takes one argument can operate as infix operator as follows
  case class MyBool(x: Boolean) {
    def and(that: MyBool): MyBool = if (x) that else this
    def or(that: MyBool): MyBool = if (x) this else that
    def negate: MyBool = MyBool(!x)
    def not(x: MyBool) = x.negate
    def xor(x: MyBool) = (x or this) and not(x and this)

    override def toString=x.toString
  }

  val p1=MyBool(true)
  val p2=MyBool(false)

  println(p1 and p2)
  println(p1.or(p2))
  println(p1.negate)
  println(p1.not(p2))
  println(p2.xor(p1))


}
