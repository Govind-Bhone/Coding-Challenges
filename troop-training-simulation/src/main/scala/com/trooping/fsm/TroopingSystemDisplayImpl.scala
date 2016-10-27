package com.trooping.fsm

/*
 * Display trait for defining display messages on screen
 * 
 */
trait TroopingSystemDisplayImpl extends Logger {

  def displayStartSystemMessage = {
    log(INFO,"#############Trooping System#########################")
    log(INFO, "1. Troop training > start training")
    log(INFO, "2. Exit")
    log(INFO, "What do you want to do ?")
  }

  def displayStartTrainingMessage = {
    log(INFO, "1. Train troops")
    log(INFO, "2. view troop camp")
    log(INFO, "What do you want to do ?")
  }

  def displayInputTypeOfTrooperMessage = {
    log(INFO, "1. Archer")
    log(INFO, "2. Barbarian")
    log(INFO, "Which troop you want to train ?")
  }

}