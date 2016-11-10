object ArraysLeftRotate extends App{
  //=======================Using Drop and Take ==========================
  def rotateRight(A: Array[Int], K: Int): Array[Int] = {
    if (null == A || A.length == 0) A else (A.drop(A.size - (K % A.length-1)) ++ A.take(A.size - (K % A.length-1)))
  }

  //======================Using SplitAt Method =========================
  def rotateRight1(A: Array[Int], K: Int): Array[Int] = {
    if (null == A || A.length == 0) A else {
      val (first,second) = A.splitAt(K)
      second ++ first
    }
  }

  //=======================Using Drop and Take ==========================
  def rotateRight2(A: Array[Int], K: Int): Array[Int] = {
    if (null == A || A.length == 0) A else (A.drop(K) ++ A.take(K))
  }

  println(rotateRight(Array(1,2,3,4,5), 3).mkString(" "))
  println(rotateRight1(Array(1,2,3,4,5), 3).mkString(" "))
  println(rotateRight2(Array(1,2,3,4,5), 3).mkString(" "))


}
