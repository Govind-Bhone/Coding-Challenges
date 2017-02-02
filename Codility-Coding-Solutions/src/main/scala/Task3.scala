object Task3 extends App {

  def solution(input1: String, input2: String): Boolean = {
    var inputexpanded1 = new scala.collection.mutable.StringBuilder
    var inputexpanded2 = new scala.collection.mutable.StringBuilder
    inputexpanded1 = expandInput(input1, inputexpanded1)
    inputexpanded2 = expandInput(input2, inputexpanded2)
    var finalOutput = false
    if (inputexpanded1.length != inputexpanded2.length)
      return false
    else {
      scala.util.control.Breaks.breakable {
        for (i <- 0 to inputexpanded1.length - 1) {
          if (inputexpanded1.charAt(i) == inputexpanded2.charAt(i) ||
            Character.isLetter(inputexpanded1.charAt(i)) && inputexpanded2.charAt(i) == '?'
            || Character.isLetter(inputexpanded2.charAt(i)) && inputexpanded1.charAt(i) == '?') {
            finalOutput = true
          } else {
            finalOutput = false
            scala.util.control.Breaks.break()
          }
        }
      }
    }
    return finalOutput
  }

  def expandInput(input: String, extendedInput: StringBuilder): StringBuilder = {
    for (i <- 0 to input.length - 1) {
      val tempHolder = input.charAt(i)
      if (Character.isDigit(input.charAt(i))) {
        for (k <- 0 until Character.getNumericValue(tempHolder)) {
          extendedInput.append("?")
        }
      } else {
        extendedInput.append(tempHolder)
      }
    }
    return extendedInput
  }

  println(solution("ba1", "1Ad")) // ba?   ?Ad
  println(solution("A2Le", "2pL1"))
  println(solution("a10", "10a"))
  println(solution("1","1"))

}
