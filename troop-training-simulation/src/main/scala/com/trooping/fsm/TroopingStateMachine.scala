package com.trooping.fsm

sealed trait FSMState

/*
 * Trooper state Enumerator object
 */
object TroopingState extends Enumeration {
  type State = Value
  val StartTroopingSystemState, StartTrainingState, InputMagicPotionsState, TrainTroops, ViewTroopCampState, InputTroopTypeAndValidateState, StartTraining,CheckBarrackCapacityState, InputUserPreferenceState, SendToTroopCampState, ExitSystemState = Value
}

/*
 * Event Definition for FSM transition
 */
trait Event
case object StartSystem extends Event
case object StartTraining extends Event
case object GetMagicPotions extends Event
case object TrainTroops extends Event
case object ViewTroopCamp extends Event
case class GetInputTrooperTypeAndTrain(noOfTroopers:Int,trooperType:TrooperType) extends Event 
case object TrainArcher extends Event
case object TrainBarbarian extends Event
case object ValidateBarracksCapacity extends Event
case object GetUserPreference extends Event
case object SendToTroopCamp extends Event
case object ExitSystem extends Event

/*
 * FSM State object
 */
case class TroopingSystem(trooperState: TroopingState.State) extends FSMState

