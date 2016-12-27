package example.merchant.galaxy.logic

/**
  * Created by govind.bhone on 12/26/2016.
  */

case class InvalidTransactionUnit(msg:String) extends Exception(msg)
case class RomanDigitRepeatedError(msg:String) extends Exception(msg)
case class InvalidToken(msg:String) extends Exception(msg)
