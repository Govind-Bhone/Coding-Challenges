/**
  * Created by govind.bhone on 12/2/2016.
  */
object P26 extends App{
  // P26 (**) Generate the combinations of K distinct objects chosen from the N
  //          elements of a list.
  //     In how many ways can a committee of 3 be chosen from a group of 12
  //     people?  We all know that there are C(12,3) = 220 possibilities (C(N,K)
  //     denotes the well-known binomial coefficient).  For pure mathematicians,
  //     this result may be great.  But we want to really generate all the possibilities.
  //
  //     Example:
  //     scala> combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
  //     res0: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), ...

  def combinations[A](k:Int,xs:List[A]):List[List[A]]=xs.combinations(k).toList

  println(combinations(3, List('a, 'b, 'c, 'd, 'e, 'f)))
  println(combinations(2, List(1, 2, 3,4)))


  object TimerStatus extends Enumeration {
    type Status = Value
    val InProgress = Value(1, "Pause Timer")
    val Paused = Value(-1, "Resume Timer")
  }

  val const=TimerStatus.withName("Pause Timer")
  const



}
