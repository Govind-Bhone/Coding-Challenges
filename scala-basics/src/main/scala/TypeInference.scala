/**
  * Created by govind.bhone on 11/20/2016.
  */
object TypeInference extends App {

  case class MyPair[A, B](x: A, y: B);

  def id[T](x: T) = x

  val p = MyPair(1, "scala")
  // type: MyPair[Int, String]
  val q = id(1) // type: Int

  val x = 1 + 2 * 3
  // the type of x is Int
  val y = x.toString()

  // the type of y is String
  def succ(x: Int) = x + 1

  // method succ returns Int values

  //In some situations it can be quite dangerous to rely on Scala’s
  // type inference mechanism as the following program shows , so compiler warns to provide it explicitly
  object InferenceTest4 {
    var obj: Object = null
    obj = new Object()
  }

  //Also in Recursive algo we need to provide type else it will give compile time warning
  def fac(n: Int): Int = if (n == 0) 1 else n * fac(n - 1)

}
