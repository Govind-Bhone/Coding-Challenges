/**
  * Created by govind.bhone on 11/20/2016.
  */
object CompoundTypes extends App {

  trait DerivedClonnable extends java.lang.Cloneable {
    override def clone() = {
      try {
        super.clone()
      }
      catch {
        case ex: CloneNotSupportedException => ex.getStackTrace()
      }
    }
  }

  trait Reset {
    def reset = "Resetting the object successfully"
  }

  def cloneAndReset(obj: DerivedClonnable with Reset) = {
    val cloned = obj.clone()
    obj.reset
    cloned
  }

  case object Clone extends DerivedClonnable with Reset

  val s = Clone
  println(cloneAndReset(s))
  println(cloneAndReset(new DerivedClonnable with Reset))
}
