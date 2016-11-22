/**
  * Created by govind.bhone on 11/22/2016.
  */

import scala.util.control._
class Day18{
  val stack=new scala.collection.mutable.Stack[Char]
  val queue=new scala.collection.mutable.Queue[Char]
  def pushCharacter(c:Char)=stack.push(c)
  def enqueueCharacter(c:Char)=queue.enqueue(c)
  def popCharacter()=stack.pop
  def dequeueCharacter()=queue.dequeue
}

object Day18 {
  def main(args: Array[String]) {
    // read the string s
    var s=readLine();
    // create the Solution class object p
    var obj=new Day18();
    var i=0;
    var len=s.length();
    //push/enqueue all the characters of string s to stack
    for(i<- 0 to len-1){
      obj.pushCharacter(s.charAt(i));
      obj.enqueueCharacter(s.charAt(i));
    }

    var isPalindrome=true;
    /*pop the top character from stack
      dequeue the first character from queue
      compare both the characters*/

    val loop = new Breaks;
    loop.breakable {
      for(i<- 0 to len/2){
        if(obj.popCharacter()!=obj.dequeueCharacter()){
          isPalindrome=false;
          loop.break;
        }
      }
    }

    //finally print whether string s is palindrome or not
    if(isPalindrome){
      println("The word, "+s+", is a palindrome." );
    }
    else{
      println("The word, "+s+", is not a palindrome." );
    }
  }
}
