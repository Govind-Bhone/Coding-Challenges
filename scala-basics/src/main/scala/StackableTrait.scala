/**
  * Created by govind.bhone on 11/19/2016.
  */
trait SealedTrait {
  def greet(): String
}

trait Friendly extends SealedTrait {
  override def greet() = {
    println("In Hi")
    "Hi"
  }
}

trait Dog extends Friendly {
  override def greet() = {
    println("In Dog")
    "Woof"
  }
}

trait HungryCat extends Friendly {
  override def greet() = {
    println("HungryCat")
    "Meow"
  }
}

trait HungryDog extends Friendly {
  override def greet() = {
    println("hungrey dog")
    "I'd like to eat my own dog food " + super.greet()
  }
}

class ExclamatoryGreeter extends HungryCat /*with HungryDog*/{
  override def greet() = {
    println("ExclamatoryGreeter")
    super.greet() + "!"
  }
}

//HungryCat->Friendly->sealedTrait->AnyRef->Any
// ExlamatoryGreeter ->HungryCat->Friendly->sealedTrait->AnyRef->Any
//HungryDog ->ExlamatoryGreeter->HungryCat->Friendly->sealedTrait->AnyRef->Any

object Main extends App {
  val pet = new ExclamatoryGreeter with HungryDog
  println(pet.greet())
}