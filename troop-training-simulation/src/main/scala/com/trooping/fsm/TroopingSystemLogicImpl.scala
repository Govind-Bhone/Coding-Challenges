package com.trooping.fsm

trait TroopingSystemLogicImpl {
  this: TroopingSystemDisplayImpl =>
  import Barracks.{ filledSeats, barrackState }

  /*
   * Clone the New State 
   */
  protected def cloneNewState(d: TroopingSystem, s: TroopingState.State) =
    d.copy(trooperState = s)

  /*
   * Get the magic potions
   */
  protected def getMagicPotions = {
    log(INFO, "Enter the Total Magic potions for the Training :")
    readInt
  }

  /*
   * Train the troopers in Barracks
   */
  protected def train(noofTroopers: Int, trooperType: TrooperType) = {
    log(INFO, "training........")
    for (i <- 0 to noofTroopers) {
      Thread.sleep(trooperType.time*Barracks.SECOND_IN_MILLISECONDS)
    }
    log(INFO, "training completed")
    MagicPotions.remove(noofTroopers * trooperType.cost)
    barrackState.get(trooperType) match {
      case Some(count) => barrackState.put(trooperType, count + noofTroopers)
      case None => barrackState.put(trooperType, noofTroopers)
    }
    filledSeats = filledSeats + noofTroopers
  }

  def exitSystem = { log(WARN, "Exiting the Application ......."); sys.exit() }

  def readLine = Console.readLine("prompt>").trim()
  def readInt: Int = try {
    Console.readLine("prompt>").trim().toInt
  } catch {
    case ex: Exception => sys.error("Number format exception"); -1
  }

}