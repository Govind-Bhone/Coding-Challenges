import scala.util.{Failure, Success}

/**
  * Created by govind.bhone on 11/20/2016.
  */
object FutureExample extends App{

//  import scala.util.Try
//  // Some type aliases, just for getting more meaningful method signatures:
//  type CoffeeBeans = String
//  type GroundCoffee = String
//  case class Water(temperature: Int)
//  type Milk = String
//  type FrothedMilk = String
//  type Espresso = String
//  type Cappuccino = String
//  // dummy implementations of the individual steps:
//  def grind(beans: CoffeeBeans): GroundCoffee = s"ground coffee of $beans"
//  def heatWater(water: Water): Water = water.copy(temperature = 85)
//  def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"
//  def brew(coffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"
//  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"
//  // some exceptions for things that might go wrong in the individual steps
//  // (we'll need some of them later, use the others when experimenting
//  // with the code):
//  case class GrindingException(msg: String) extends Exception(msg)
//  case class FrothingException(msg: String) extends Exception(msg)
//  case class WaterBoilingException(msg: String) extends Exception(msg)
//  case class BrewingException(msg: String) extends Exception(msg)
//  // going through these steps sequentially:
//  def prepareCappuccino(): Try[Cappuccino] = for {
//    ground <- Try(grind("arabica beans"))
//    water <- Try(heatWater(Water(25)))
//    espresso <- Try(brew(ground, water))
//    foam <- Try(frothMilk("milk"))
//  } yield combine(espresso, foam)

  import scala.concurrent.future
  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration._
  import scala.util.Random

  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("start grinding...")
    Thread.sleep(Random.nextInt(2000))
    if (beans == "baked beans") throw GrindingException("are you joking?")
    println("finished grinding...")
    s"ground coffee of $beans"
  }

  def heatWater(water: Water): Future[Water] = Future {
    println("heating the water now")
    Thread.sleep(Random.nextInt(2000))
    println("hot, it's hot!")
    water.copy(temperature = 85)
  }

  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    println("milk frothing system engaged!")
    Thread.sleep(Random.nextInt(2000))
    println("shutting down milk frothing system")
    s"frothed $milk"
  }

  def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("happy brewing :)")
    Thread.sleep(Random.nextInt(2000))
    println("it's brewed!")
    "espresso"
  }

  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"

 /* object Future {
    def apply[T](body: => T)(implicit execctx: ExecutionContext): Future[T]
  }*/


  grind("arabica beans").onSuccess { case ground =>
    println("okay, got my ground coffee")
  }

  grind("arabica beans").onComplete{
    case Success(ground)=>println("okay, got my ground coffee")
    case Failure(ex)=>println("error in grinding")
  }

  val temperatureOkay: Future[Boolean] = heatWater(Water(25)).map { water =>
    println("we're in the future!")
    (80 to 85).contains(water.temperature)
  }


  //Blocking for comprehension \
  def prepareCappuccinoSequentially(): Future[Cappuccino] = {
    for {
      ground <- grind("arabica beans")
      water <- heatWater(Water(20))
      foam <- frothMilk("milk")
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
  }

  //Concurrent for comprehension execution
  def prepareCappuccino(): Future[Cappuccino] = {
    val groundCoffee = grind("arabica beans")
    val heatedWater = heatWater(Water(20))
    val frothedMilk = frothMilk("milk")
    for {
      ground <- groundCoffee
      water <- heatedWater
      foam <- frothedMilk
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
  }


  //========================Creating future

  import concurrent.Future
  import concurrent.ExecutionContext.Implicits.global
  val f: Future[String] = Future { "Hello world!" }

  import concurrent.Promise
  case class TaxCut(reduction: Int)
  // either give the type as a type parameter to the factory method:
  val taxcut = Promise[TaxCut]()
  // or give the compiler a hint by specifying the type of your val:
  val taxcut2: Promise[TaxCut] = Promise()

  val taxcutF: Future[TaxCut] = taxcut.future

  taxcut.success(TaxCut(20))

}
