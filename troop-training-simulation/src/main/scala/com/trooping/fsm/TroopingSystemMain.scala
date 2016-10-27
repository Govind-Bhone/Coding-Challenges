package com.trooping.fsm

/*
 * Trooping System Main class 
 * 
 */
object TroopingSystemMain extends TroopingSystemLogicImpl with TroopingSystemDisplayImpl {
  import Barracks._
  var choice = ""

  /*
   * transition function for FSM transition based on event and state clonning
   */
  def transition[T <: Event](d: TroopingSystem, e: T): TroopingSystem = {
    d.trooperState match {
      case TroopingState.StartTroopingSystemState =>
        displayStartSystemMessage
        choice = readLine
        choice match {
          case "1" => transition(cloneNewState(d, TroopingState.InputMagicPotionsState), GetMagicPotions)
          case "2" => transition(cloneNewState(d, TroopingState.ExitSystemState), ExitSystem)
          case _ =>
            log(WARN, "Invalid Input Detected...Returning system to previous state ")
            transition(cloneNewState(d, TroopingState.StartTroopingSystemState), StartSystem)
        }

      case TroopingState.InputMagicPotionsState =>
        MagicPotions.totalMagicPotions = getMagicPotions
        transition(cloneNewState(d, TroopingState.StartTrainingState), StartTraining)
      case TroopingState.StartTrainingState =>
        displayStartTrainingMessage
        choice = readLine
        choice match {
          case "1" => transition(cloneNewState(d, TroopingState.TrainTroops), TrainTroops)
          case "2" => transition(cloneNewState(d, TroopingState.ViewTroopCampState), ViewTroopCamp)
          case _ =>
            log(WARN, "Invalid Input Detected...Returning system to previous state ")
            transition(cloneNewState(d, TroopingState.StartTrainingState), StartTraining)
        }
      case TroopingState.TrainTroops =>
        displayInputTypeOfTrooperMessage
        choice = readLine
        choice match {
          case "1" => transition(cloneNewState(d, TroopingState.InputTroopTypeAndValidateState), TrainArcher)
          case "2" => transition(cloneNewState(d, TroopingState.InputTroopTypeAndValidateState), TrainBarbarian)
          case _ =>
            log(WARN, "Invalid Input Detected...Returning system to previous state ")
            transition(cloneNewState(d, TroopingState.TrainTroops), TrainTroops)
        }
      case TroopingState.ViewTroopCampState =>
        TroopCamp.print
        transition(cloneNewState(d, TroopingState.StartTrainingState), StartTraining)
      case TroopingState.InputTroopTypeAndValidateState =>
        e match {
          case TrainArcher =>
            log(INFO, "how many archers you want to train ?")
            val noOfArchers = readInt
            if (validate(noOfArchers, Archer))
              transition(cloneNewState(d, TroopingState.StartTraining), GetInputTrooperTypeAndTrain(noOfArchers, Archer))
            else
              transition(cloneNewState(d, TroopingState.ExitSystemState), ExitSystem)
          case TrainBarbarian =>
            log(INFO, "how many Barbarians you want to train ?")
            val noOfBarbarian = readInt
            if (validate(noOfBarbarian, Barbarian))
              transition(cloneNewState(d, TroopingState.StartTraining), GetInputTrooperTypeAndTrain(noOfBarbarian, Barbarian))
            else
              transition(cloneNewState(d, TroopingState.ExitSystemState), ExitSystem)
          case _ =>
            log(WARN, "Invalid TrooperType Detected...Returning system to previous state ")
            transition(cloneNewState(d, TroopingState.TrainTroops), TrainTroops)
        }
      case TroopingState.StartTraining =>
        e match {
          case GetInputTrooperTypeAndTrain(noofTroopers, trooperType) =>
            train(noofTroopers, trooperType)
            transition(cloneNewState(d, TroopingState.CheckBarrackCapacityState), ExitSystem)
          case _ =>
            log(WARN, "Invalid Message Detected...Returning system to previous state ")
            transition(cloneNewState(d, TroopingState.ExitSystemState), ExitSystem)
        }
      case TroopingState.CheckBarrackCapacityState =>
        if (isBarrackFull)
          transition(cloneNewState(d, TroopingState.SendToTroopCampState), SendToTroopCamp)
        else
          transition(cloneNewState(d, TroopingState.TrainTroops), TrainTroops)
      case TroopingState.SendToTroopCampState =>
        sendIntoTroopCamp
        updateBarrackState
        transition(cloneNewState(d, TroopingState.StartTrainingState), StartTraining)
      case TroopingState.ExitSystemState =>
        exitSystem
      case _ =>
        log(WARN, "Invalid State Found")
        transition(cloneNewState(d, TroopingState.ExitSystemState), ExitSystem)
    }
  }

  /*
   * Main Method for Trooping system 
   */
  def main(args: Array[String]) {
    transition(TroopingSystem(TroopingState.StartTroopingSystemState), StartSystem)
  }
}