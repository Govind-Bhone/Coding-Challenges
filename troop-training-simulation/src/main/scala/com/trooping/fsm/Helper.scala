package com.trooping.fsm

import scala.collection.mutable.HashMap
/*
 * magic potions are the credits point to train the trooper in barracks
 * this object keep track of magic potion utilization
 */
object MagicPotions {
  var totalMagicPotions = 0
  def remove(potions: Int) = totalMagicPotions = totalMagicPotions - potions
  def add(potions: Int) = totalMagicPotions = totalMagicPotions + potions
}

/*
 * Trooper have two properties 
 * 1. time => duration for the training
 * 2. cost => cost for the training 
 */
sealed trait TrooperType {
  def time: Int
  def cost: Int
}

case object Barbarian extends TrooperType { override val time: Int = 3; override val cost: Int = 10 }
case object Archer extends TrooperType { override val time: Int = 6; override val cost: Int = 20 }

/*
 * After successful completion of the training ,troopers will be available in 
 * troop camp. this static object keeps track of it . 
 */
object TroopCamp {
  lazy val troopCampDetails = new HashMap[TrooperType, Int]()

  def print = {
    println("Troop Details are :")
    println(s"Archers :${troopCampDetails.getOrElse(Archer, 0)}")
    println(s"Barbarian :${troopCampDetails.getOrElse(Barbarian, 0)}")
  }
}

/*
 * State Model and Behavior definition for Barrack 
 */
object Barracks extends Logger {
  val BARRACKS_TOTAL_CAPACITY = 10
  val SECOND_IN_MILLISECONDS = 1000
  var filledSeats = 0
  lazy val barrackState = new HashMap[TrooperType, Int]()

  /*
   * Validate the barracks and the magic potions availability
   */
  def validate(noOfTroopers: Int, trooper: TrooperType): Boolean = {
    if (noOfTroopers + filledSeats <= BARRACKS_TOTAL_CAPACITY) {
      if ((trooper.cost * noOfTroopers) <= MagicPotions.totalMagicPotions)
        true
      else {
        log(WARN, "you don't have sufficient magic potions....")
        false
      }
    } else {
      log(WARN, "Barracks reaches its maximum capacity ")
      false
    }
  }

  /*
   * check the barrack capacity
   */
  def isBarrackFull: Boolean = {
    if (filledSeats == BARRACKS_TOTAL_CAPACITY) {
      log(INFO, "troops are available in the troop camp")
      true
    } else {
      log(INFO, "we still have some space for trooper to train in barracks")
      false
    }
  }

  /*
   * update Barrack state after sending troopers into troop camp
   * 
   */

  def updateBarrackState = barrackState.empty; filledSeats = 0

  /*
   * update the troop camp details after successful completion of training 
   * 
   */
  def sendIntoTroopCamp() {
    barrackState.foreach(trooperInfo => {
      TroopCamp.troopCampDetails.get(trooperInfo._1) match {
        case Some(count) => TroopCamp.troopCampDetails.update(trooperInfo._1, trooperInfo._2 + count)
        case None => TroopCamp.troopCampDetails.put(trooperInfo._1, trooperInfo._2)
      }
    })
  }

}