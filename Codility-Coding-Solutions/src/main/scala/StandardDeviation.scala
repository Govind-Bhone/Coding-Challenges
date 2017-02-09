/**
  * Created by govind.bhone on 2/2/2017.
  */
object StandardDeviation extends App{

  val array = Array(9, 2, 5, 4, 12, 7, 8, 11, 9, 3, 7, 4, 12, 5, 4, 10, 9, 6, 9, 4)
  var powerSum1 = 0d
  var stdev:Double = 0d

  val mean = array.toList.sum/array.length
  for(i<-0 to array.length-1){
    powerSum1 += Math.pow((array(i)-mean),2)
  }
  stdev = Math.sqrt(powerSum1/array.length)

  println(stdev)


}
