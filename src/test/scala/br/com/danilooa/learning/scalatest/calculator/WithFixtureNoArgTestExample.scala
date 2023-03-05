package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.flatspec.AnyFlatSpec

class WithFixtureNoArgTestExample extends AnyFlatSpec {

  override protected def withFixture(test: NoArgTest) = {
    try {
      println("Performing setup")
      super.withFixture(test);
    }
    finally {
      println("Performing clean up")
    }
  }

  "Any number times 0" should "be 0" in {
    val result = BasicCalculator.multiple(BigDecimal(1), BigDecimal(0))
    assert(result == 0)
  }

  "Any division by 0" should "throw an Exception" in {
    assertThrows[ArithmeticException] {
      BasicCalculator.divide(BigDecimal(1), BigDecimal(0))
    }
  }
}
