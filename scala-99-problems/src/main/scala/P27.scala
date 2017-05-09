/**
  * Created by govind.bhone on 5/9/2017.
  */
object P27 extends App{

  val list=List(11,12)

 val res = for{
    l<-list
  } yield (2 to l-1 ).filter(l%_==0)

  println(res)

}
