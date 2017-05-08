/**
  * Created by govind.bhone on 4/25/2017.
  */
object Solution2 extends App {

  def sliceOfArray(a:Array[Int],length:Int)={

    def loop (start:Int,length:Int,numOfSlices:Int):Int={
      if(start >= length-2){
        if (numOfSlices > 1000000000) -1 else numOfSlices
      }else{
        var end = start + 1
        var diff = a(end) - a(start)
        while (end < length - 1 && a(end + 1) - a(end) == diff) {
          end += 1
        }
        var lenOfSlice = end - start + 1; //length of the found arightmetic slice
        //valid slice length >3
        if (lenOfSlice >= 3) {
          // (n-2)*(n-1)/2
          loop(end,length,numOfSlices +((lenOfSlice - 2) * (lenOfSlice - 1) / 2))
        }else{
          loop (end,length,numOfSlices)
        }
      }
    }
    loop(0,length,0)
  }
  def solution(a: Array[Int]): Int = {
    val length = a.length

    length match {
      case len if len < 3 => 0
      case _ =>sliceOfArray(a,length)
    }
  }

  println(solution(Array(-1, 1, 3, 3, 3, 2, 1, 0)))
  println(solution(Array(-1, 1, 3,4,5,6)))

}
