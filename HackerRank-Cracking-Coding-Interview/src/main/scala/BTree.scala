/**
  * Created by govind.bhone on 11/20/2016.
  */
trait BTree[+A] {
  def insert[B >: A <% Ordered[B]](elem: B): BTree[B]

  def left: BTree[A] = this match {
    case EmptyBTree => EmptyBTree
    case NonEmptyBTree(x, l, r) => l
  }

  def right: BTree[A] = this match {
    case EmptyBTree => EmptyBTree
    case NonEmptyBTree(x, l, r) => r
  }

  def findElement(p: A => Boolean): Boolean

  def inOrder[B](z: B)(f: (A, B) => B): B

  def preOrder[B](z: B)(f: (A, B) => B): B

  def postOrder[B](z: B)(f: (A, B) => B): B

  def isBTree[B >: A <% Ordered[B]](min: B, max: B): Boolean

}

case object EmptyBTree extends BTree[Nothing] {
  def insert[B <% Ordered[B]](elem: B) = BTree(elem)

  def findElement(p: Nothing => Boolean) = false

  def isBTree[B <% Ordered[B]](min: B, max: B): Boolean = true

  def inOrder[B](z: B)(f: (Nothing, B) => B) = z

  def preOrder[B](z: B)(f: (Nothing, B) => B) = z

  def postOrder[B](z: B)(f: (Nothing, B) => B) = z
}

case class NonEmptyBTree[+A](elem: A, override val left: BTree[A], override val right: BTree[A]) extends BTree[A] {

  def addLeft[B >: A <% Ordered[B]](newLeft: BTree[B]) = NonEmptyBTree(elem, newLeft, right)

  def addRight[B >: A <% Ordered[B]](newRight: BTree[B]) = NonEmptyBTree(elem, left, newRight)

  def insert[B >: A <% Ordered[B]](newElem: B): BTree[B] = {
    if (newElem < elem) addLeft(left.insert(newElem))
    else if (newElem > elem) addRight(right.insert(newElem))
    else this
  }

  def findElement(p: A => Boolean) = p(elem) || left.findElement(p) || right.findElement(p)

  def isBTree[B >: A <% Ordered[B]](min: B, max: B): Boolean = {
    min < elem && elem < max && left.isBTree(min, elem) &&
      right.isBTree(elem, max)
  }

  def inOrder[B](z: B)(f: (A, B) => B) = right.inOrder(f(elem, left.inOrder(z)(f)))(f)

  def preOrder[B](z: B)(f: (A, B) => B) = right.preOrder(left.preOrder(f(elem, z))(f))(f)

  def postOrder[B](z: B)(f: (A, B) => B) = f(elem, right.postOrder(left.postOrder(z)(f))(f))
}

object BTree {
  def apply[A <% Ordered[A]](): BTree[A] = EmptyBTree

  def apply[A <% Ordered[A]](elem: A, elems: A*): BTree[A] = {
    def recurse(elems: scala.List[A], bst: BTree[A]): BTree[A] =
      if (elems.isEmpty) bst
      else recurse(elems.tail, bst.insert(elems.head))

    recurse(elems.toList, NonEmptyBTree(elem, EmptyBTree, EmptyBTree))
  }

  def fold[A, B](t: BTree[A], z: B)(f: (A, B, B) => B): B = t match {
    case EmptyBTree => z
    case NonEmptyBTree(x, l, r) => f(x, fold(l, z)(f), fold(r, z)(f))
  }

  def heightOfTreeOneApproach[T](tree: BTree[T]): Int = {
    if (tree == EmptyBTree) {
      return -1
    } else {
      return 1 +
        Math.max(heightOfTreeOneApproach(tree.left),
          heightOfTreeOneApproach(tree.right));
    }
  }

  def heightOfTreeSecondApproach[T](tree: BTree[T]) = fold(tree, 0: Int) { (x, l, r) => 1 + math.max(l, r) }

  def map[A, B](tree: BTree[A])(f: A => B): BTree[B] =
    fold(tree, EmptyBTree.asInstanceOf[BTree[B]]) { (x, l, r) => NonEmptyBTree(f(x), l, r) }

  def invertTree[A, B >: A <% Ordered[B]](tree: BTree[A]): BTree[B] =
    fold(tree, EmptyBTree.asInstanceOf[BTree[B]]) { (value, leftT, rightT) => NonEmptyBTree(value, rightT, leftT) }

}

object BTreeTest extends App {

  def printTraversals[T](bst: BTree[T]) = {
    println("preOrder: " + bst.preOrder(List[T]())(_ :: _).reverse)
    println("postOrder: " + bst.postOrder(List[T]())(_ :: _).reverse)
    println("inOrder: " + bst.inOrder(List[T]())(_ :: _).reverse)
  }

  val tree = BTree[Int]().insert(10).insert(5).insert(15).insert(8)
  println(tree)
  println(tree.findElement(x => x == 5))
  println(tree.findElement(_ == 89))
  printTraversals(tree)
  println(tree.isBTree(Int.MinValue, Int.MaxValue))
  println(tree.left)
  println(BTree.heightOfTreeOneApproach(tree))
  println(BTree.heightOfTreeSecondApproach(tree))
  println(BTree.map(tree)(_ * 10))
  println(BTree.invertTree(tree))
}