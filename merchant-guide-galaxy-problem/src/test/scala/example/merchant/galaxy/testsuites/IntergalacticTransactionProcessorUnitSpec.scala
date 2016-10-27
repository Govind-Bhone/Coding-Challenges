package example.merchant.galaxy.testsuites

import org.scalatest.FunSpec
import example.merchant.galaxy.logic.{ IntergalacticTransactionProcessor, InputParser, TokenValueProcessor, QuestionProcessor, UnitsValueProcessor }
import scala.io.Source
import example.merchant.galaxy.logic.{ HowMuch, HowMany, Credits, Assignment }

class IntergalacticTransactionProcessorUnitSpec extends FunSpec with InputParser with QuestionProcessor
  with TokenValueProcessor with UnitsValueProcessor {
  import IntergalacticTransactionProcessor._
  override def filePath = null

  describe("InputStream Creation ") {
    it("inputstream successful creation") {
      val s = IntergalacticTransactionProcessor.apply1("")
      assert(s.inputstream.isSuccess == true)
    }

    it("inputstream unsuccessful creation in case of file not found") {
      val s = IntergalacticTransactionProcessor.apply1("new.txt")
      assert(s.inputstream.isFailure == true)
    }
  }

  describe("Reading content from file til EOF  ") {
    it("reading from file") {
      val s = IntergalacticTransactionProcessor.apply1("")
      val source = Source.fromInputStream(inputstream.get)
      val filecontent = """glob is Iprok is Vpish is Xtegj is Lglob glob Silver is 34 Creditsglob prok Gold is 57800 Creditspish pish Iron is 3910 Creditshow much is pish tegj glob glob ?how many Credits is glob prok Silver ?how many Credits is glob prok Gold ?how many Credits is glob prok Iron ?how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"""
      assert(readLines(source).mkString == filecontent)

    }
  }

  describe("checking the type of line") {
    it("token roman value assignment line") {
      assert(checkFlag("glob is I") == Assignment)
    }
    it("noncurrency(metal) item value line") {
      assert(checkFlag("glob glob Silver is 34 Credits") == Credits)
    }
    it("How Much Question  line") {
      assert(checkFlag("how much is pish tegj glob glob ?") == HowMuch)
    }
    it("How Many Question line") {
      assert(checkFlag("how many Credits is glob prok Iron ?") == HowMany)
    }
  }

  describe("process token roman value assignment") {
    it("token roman value map population test") {
      tokenRomanValueMapper.empty
      processTokenRomanValueAssignment("glob is I")
      assert(tokenRomanValueMapper.contains("glob") == true)
    }
  }

  describe("process non currency items value") {
    it("get decimal value of tokens") {
      tokenRomanValueMapper.empty
      tokenRomanValueMapper.put("glob", "I")
      tokenRomanValueMapper.put("prok", "V")
      assert(getDecimalValue("glob prok".split("\\s+")) == 4)
    }
    it("metal/noncurrency items per unit value") {
      tokenRomanValueMapper.empty
      tokenRomanValueMapper.put("glob", "I")
      tokenRomanValueMapper.put("prok", "V")
      processNonCurrencyItems("glob prok Gold is 57800 Credits")
      assert(metalDecimalValueMapper("Gold") == 14450.0)
    }
  }

  describe("process Questions") {
    it("process How Many question") {
      tokenRomanValueMapper.empty
      tokenRomanValueMapper.put("glob", "I")
      tokenRomanValueMapper.put("prok", "V")
      metalDecimalValueMapper("Gold") = 14450.0
      val value = processHowManyQuestion("how many Credits is glob prok Gold ?")
      assert(value._2 == 57800d)
    }
    it("process How Much question") {
      tokenRomanValueMapper.empty
      tokenRomanValueMapper.put("glob", "I")
      tokenRomanValueMapper.put("prok", "V")
      tokenRomanValueMapper.put("pish", "X")
      tokenRomanValueMapper.put("tegj", "L")
     assert(processHowMuchQuestion("how much is pish tegj glob glob ?")._2==42.0d)
    }
  }

}