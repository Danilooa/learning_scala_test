package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.flatspec.AnyFlatSpec

class BasicCalculatorFlatSpecTest extends AnyFlatSpec {

  "Any number times 0" should "be 0" in {
    val result = BasicCalculator.multiple(BigDecimal(1), BigDecimal(0))
    assert(result == 0)
  }

}
