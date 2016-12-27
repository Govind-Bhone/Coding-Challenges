package example.merchant.galaxy.testsuites

import org.junit.{Assert, Test}
import example.merchant.galaxy.logic.{IntergalacticTransactionProcessor, InputParser, TokenValueProcessor, QuestionProcessor, UnitsValueProcessor}
import scala.io.Source
import example.merchant.galaxy.logic.{HowMuch, HowMany, Credits, Assignment}

class IntergalacticTransactionProcessorUnitSpec extends InputParser with QuestionProcessor
  with TokenValueProcessor with UnitsValueProcessor {

  import IntergalacticTransactionProcessor._

  def filePath = None

  @Test
  def testInputStreamSuccessfullCreationforNone() {
    val s = IntergalacticTransactionProcessor(None)
    Assert.assertEquals(s.inputstream.isSuccess, true)
  }

  @Test
  def testInputStreamUnsuccessfullCreationforFileNotFound() {
    val s = IntergalacticTransactionProcessor(Some("new.txt"))
    Assert.assertEquals(s.inputstream.isFailure, true)
  }

  @Test
  def testReadingContentOfFiletillEOF() {
    val s = IntergalacticTransactionProcessor(None)
    val source = Source.fromInputStream(inputstream.get)
    val filecontent = """glob is Iprok is Vpish is Xtegj is Lglob glob Silver is 34 Creditsglob prok Gold is 57800 Creditspish pish Iron is 3910 Creditshow much is pish tegj glob glob ?how many Credits is glob prok Silver ?how many Credits is glob prok Gold ?how many Credits is glob prok Iron ?how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"""
    Assert.assertEquals(readLines(source).mkString, filecontent)
  }

  @Test
  def testTokenRomanAssignmentStatement() {
    Assert.assertEquals(checkFlag("glob is I"), Assignment)
  }

  @Test
  def testNonCurrencyItemValueStatement() {
    assert(checkFlag("glob glob Silver is 34 Credits") == Credits)
  }

  @Test
  def testHowManyQuestionStatement() {
    assert(checkFlag("how many Credits is glob prok Iron ?") == HowMany)
  }

  @Test
  def testHowMuchQuestionStatement() {
    assert(checkFlag("how much is pish tegj glob glob ?") == HowMuch)
  }

  @Test
  def testTokenRomanValueMapPopulation() {
    tokenRomanValueMapper.empty
    processTokenRomanValueAssignment("glob is I")
    assert(tokenRomanValueMapper.contains("glob") == true)
  }

  @Test
  def testDecimalValueOfTokens() {
    tokenRomanValueMapper.empty
    tokenRomanValueMapper.put("glob", "I")
    tokenRomanValueMapper.put("prok", "V")
    assert(getDecimalValue("glob prok".split("\\s+")) == 4)
  }

  @Test
  def testNonCurrencyItemPerUnitValue() {
    tokenRomanValueMapper.empty
    tokenRomanValueMapper.put("glob", "I")
    tokenRomanValueMapper.put("prok", "V")
    processNonCurrencyItems("glob prok Gold is 57800 Credits")
    assert(metalDecimalValueMapper("Gold") == 14450.0)
  }

  @Test
  def testProcessHowManyQuestion() {
    tokenRomanValueMapper.empty
    tokenRomanValueMapper.put("glob", "I")
    tokenRomanValueMapper.put("prok", "V")
    metalDecimalValueMapper("Gold") = 14450.0
    val value = processHowManyQuestion("how many Credits is glob prok Gold ?")
    assert(value._2 == 57800d)
  }

  @Test
  def testProcessHowMuchQuestion {
    tokenRomanValueMapper.empty
    tokenRomanValueMapper.put("glob", "I")
    tokenRomanValueMapper.put("prok", "V")
    tokenRomanValueMapper.put("pish", "X")
    tokenRomanValueMapper.put("tegj", "L")
    assert(processHowMuchQuestion("how much is pish tegj glob glob ?")._2 == 42.0d)
  }

}