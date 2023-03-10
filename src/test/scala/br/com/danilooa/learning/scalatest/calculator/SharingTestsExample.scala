package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.flatspec.AnyFlatSpec

//Write a stack function, in this case this trait to contain the tests to reuse
trait BasicCalculatorBehaviors {
  this: AnyFlatSpec =>

  //The test itself
  def divisionByZeroNotAllowed(anyNumber: BigDecimal) {
    it should "throws an Exception" in {
      assertThrows[ArithmeticException] {
        BasicCalculator.divide(anyNumber, 0)
      }
    }
  }

}

//Mix the stack of tests
class SharingTestsExample extends AnyFlatSpec with BasicCalculatorBehaviors {

  {
    val four = BigDecimal(4)
    val one = BigDecimal(1)

    "4 divided by 1" should "be 4" in {
      assert(BasicCalculator.divide(four, one) == four)
    }
    //White the DSL "it should behave" to reuse a test (a function from the stack trait"
    it should behave like divisionByZeroNotAllowed(four)

    /*
     If you call the same test more than once,
     it will throw an exception because the same test name will have been used twice.
     To avoid that prepend the call with "behaviour"
     */
    behavior of "second attempt with the same name"
    it should behave like divisionByZeroNotAllowed(BigDecimal(4))

    behavior of "third attempt with the same name"
    it should behave like divisionByZeroNotAllowed(BigDecimal(4))
  }
}

