/**
  * Created by govind.bhone on 11/20/2016.
  */
object AutoMaticTypeDependentClosure extends App {

  //=====================Example 1=============================
  def whileLoop(cond: => Boolean)(body: => Unit): Unit =
    if (cond) {
      body
      whileLoop(cond)(body)
    }

  var i = 10
  whileLoop(i > 0) {
    println(i)
    i -= 1
  }

  //========================Example 2 ============================

  def loop(body: => Unit): LoopUnlessCond =
    new LoopUnlessCond(body)

  protected class LoopUnlessCond(body: => Unit) {
    def unless(cond: => Boolean) {
      body
      if (!cond) unless(cond)
    }
  }

  var j = 10
  loop {
    println("j = " + j)
    j -= 1
  } unless (j == 0)
}
