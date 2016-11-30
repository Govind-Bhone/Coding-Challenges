/**
  * Created by govind.bhone on 11/20/2016.
  */
object P02 extends App {

  def lastButOne[T](xs: List[T]): T = {
    xs match {
      case Nil => throw new NoSuchElementException("List is empty")
      case h2 :: h1 :: Nil => h2
      case _ :: tail => lastButOne(tail)
    }
  }

  assert(lastButOne(List(1, 2, 3, 4, 5))==4)

  def lastButOneBuiltin[A](ls: List[A]): A =
    if (ls.isEmpty) throw new NoSuchElementException
    else ls.init.last

  def lastNthBuiltin[A](n: Int, ls: List[A]): A = {
    if (n <= 0) throw new IllegalArgumentException
    if (ls.length < n) throw new NoSuchElementException
    ls.takeRight(n).head
  }

  def lastNthBuiltin1[A](n: Int, ls: List[A]): A = {
    if (n <= 0) throw new IllegalArgumentException
    if (ls.length < n) throw new NoSuchElementException
    ls.splitAt(n)._1.last
  }

  assert(lastNthBuiltin1(4, List(1, 2, 3, 4, 5))==4)


  def lastNthRecursiveNonBuildIn[A](n: Int, ls: List[A]): A = {
    def loop[A](xs: List[A], n: Int) = {
      ls match {
        case Nil if n > 0 => throw new NoSuchElementException
        case _ if n == 1 => ls.last
        case _ => lastNthRecursiveNonBuildIn(n - 1, ls.init)
      }
    }
    if (n <= 0) throw new IllegalArgumentException else loop(ls, n)
  }


  // Here's one approach to a non-builtin solution.
  def lastNthRecursive[A](n: Int, ls: List[A]): A = {
    def lastNthR(count: Int, resultList: List[A], curList: List[A]): A = {
      curList match {
        case Nil if count > 0 => throw new NoSuchElementException
        case Nil => resultList.head
        case _ :: tail =>
          lastNthR(count - 1,
            if (count > 0) resultList else resultList.tail,
            tail)
      }
    }


    if (n <= 0) throw new IllegalArgumentException
    else lastNthR(n, ls, ls)
  }

  assert(lastNthRecursive(3, List(1, 2, 3, 4))==2)
  assert(lastNthRecursiveNonBuildIn(3, List(1, 2, 3, 4))==2)

}
