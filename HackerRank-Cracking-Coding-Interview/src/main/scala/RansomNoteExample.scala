/**
  * Created by govind.bhone on 11/11/2016.
  */

object RansomNoteExample {

  def isReplica(magazine: Array[String], ransom: Array[String]) = {
    val magazineMap = new scala.collection.mutable.HashMap[String, Int]()
    val ransomMap = new scala.collection.mutable.HashMap[String, Int]()

    for (mWord <- magazine) {
      if (!magazineMap.contains(mWord)) magazineMap.put(mWord, 1)
      else magazineMap.put(mWord, magazineMap(mWord) + 1)
    }

    for (rWord <- ransom) {
      if (!ransomMap.contains(rWord)) ransomMap.put(rWord, 1)
      else ransomMap.put(rWord, ransomMap(rWord) + 1)
    }

    var occurances = true

    for (rWord <- ransomMap.keySet) {
      if (!magazineMap.contains(rWord) || ransomMap(rWord) > magazineMap(rWord)) occurances = false
    }
    if (occurances) "Yes" else "No"
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    var m = sc.nextInt()
    var n = sc.nextInt()
    var magazine = new Array[String](m)
    for (magazine_i <- 0 to m - 1) {
      magazine(magazine_i) = sc.next()
    }
    var ransom = new Array[String](n)
    for (ransom_i <- 0 to n - 1) {
      ransom(ransom_i) = sc.next()
    }
    println(isReplica(magazine, ransom))
  }
}