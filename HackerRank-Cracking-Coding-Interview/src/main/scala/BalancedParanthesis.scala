/**
  * Created by govind.bhone on 11/17/2016.
  */
object BalancedParanthesisTest extends App {

  def isBalanced(expression: String): String = {
    var stack = new scala.collection.mutable.Stack[Char]()
    var result = true
    scala.util.control.Breaks.breakable{
      for(c<-expression){
        c match {
          case '[' => stack.push('[')
          case '{' => stack.push('{')
          case '(' => stack.push('(')
          case _ =>
            if (stack.isEmpty) {
              result = false
              scala.util.control.Breaks.break()
            } else {
              val top = stack.pop()
              if ((top == '(' && c != ')')
                || (top == '[' && c != ']')
                || (top == '{' && c != '}')
              ) {
                result = false
                scala.util.control.Breaks.break()
              }
            }
        }
      }
    }
    return  if(result && stack.isEmpty)"YES" else "NO"
  }

  println(isBalanced("[[]]"))
  println(isBalanced("{[(])}"))


}
