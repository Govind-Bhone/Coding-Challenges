/**
  * Created by govind.bhone on 11/22/2016.
  */


//This is the java version i am pasting here

//import java.io.*;
//import java.util.*;
//
//interface AdvancedArithmetic{
//  int divisorSum(int n);
//}
//
////Write your code here
//class Calculator implements AdvancedArithmetic {
//  public int divisorSum(int n){
//    int sum = n ;
//    for(int i=1,half=n/2;i<=half ;i++){
//      if(n%i==0)sum+=i;
//    }
//    return sum ;
//  }
//}
//
//class Solution {
//
//  public static void main(String[] args) {
//    Scanner scan = new Scanner(System.in);
//    int n = scan.nextInt();
//    scan.close();
//
//    AdvancedArithmetic myCalculator = new Calculator();
//    int sum = myCalculator.divisorSum(n);
//    System.out.println("I implemented: " + myCalculator.getClass().getInterfaces()[0].getName() );
//    System.out.println(sum);
//  }
//}

trait AdvancedArithmetic {
  def divisorSum(n: Int): Int
}

class Day19 {
  def divisorSum(n: Int): Int = {
    (1 to n / 2).filter(n % _ == 0).fold(n) {
      _ + _
    }
  }
}

object Day19 extends App {
  val n = readInt()
  val sum=(new Day19).divisorSum(1)
  println("I implemented: " + (new Day19).getClass())
  println(sum)
}
