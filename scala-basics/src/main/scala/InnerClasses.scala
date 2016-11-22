/**
  * Created by govind.bhone on 11/20/2016.
  */
object InnerClasses extends App{
  class Graph {
    class Node {
      var connectedNodes: List[Node] = Nil
      def connectTo(node: Node) {
        if (connectedNodes.find(node.equals(_)).isEmpty) {
          connectedNodes = node :: connectedNodes
        }
      }

      override def toString="Node "
    }
    var nodes: List[Node] = Nil
    def newNode: Node = {
      val res = new Node
      nodes = res :: nodes
      res
    }
  }

  val g: Graph = new Graph
  val n1: g.Node = g.newNode
  val n2: g.Node = g.newNode
  val n3: g.Node = g.newNode
  println(n3)
  n1.connectTo(n2)
  n3.connectTo(n1)

}
