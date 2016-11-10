/**
  * Created by govind.bhone on 11/10/2016.
  */
object Solution12 {

  def maxHourglassSum(arr:Array[Array[Int]])={
    var maxSum=Int.MaxValue + 1
    for(i<-0 to 3 ){
      for( j<- 0 to 3 ){
        val sum = arr(i)(j)+ arr(i)(j+1) + arr(i)(j+2) +
          arr(i+1)(j+1)+
          arr(i+2)(j)+arr(i+2)(j+1)+arr(i+2)(j+2)
        if(sum > maxSum)
          maxSum=sum
      }
    }
    maxSum

  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var arr = Array.ofDim[Int](6,6);
    for(arr_i <- 0 to 6-1) {
      for(arr_j <- 0 to 6-1){
        arr(arr_i)(arr_j) = sc.nextInt();
      }
    }
    println(maxHourglassSum(arr))

  }
}
