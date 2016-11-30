/**
  * Created by govind.bhone on 11/20/2016.
  */
object FunctionComposition extends App {

  def f(s: String) = "f(" + s + ")"

  def g(s: String) = "g(" + s + ")"

  val fComposeG = f _ compose g _
  println(fComposeG("govind"))

  val fAndThenG = f _ andThen g _
  println(fAndThenG("govind"))

  val one: PartialFunction[Int, String] = { case 1 => "one" }
  val two: PartialFunction[Int, String] = { case 2 => "two" }
  val three: PartialFunction[Int, String] = { case 3 => "three" }
  val wildcard: PartialFunction[Int, String] = { case _ => "something else" }

  val partial = one orElse two orElse three orElse wildcard

  println(partial.isDefinedAt(3))
  println(partial(3))
}
