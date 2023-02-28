package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.funsuite.AnyFunSuite

class BasicCalculatorFunSuitTest extends AnyFunSuite {

  test("Any number times 0 should be 0") {
    val value: BigDecimal = BasicCalculator.multiple(BigDecimal(4), BigDecimal(0))
    println("---> " + value)
    assert(value == BigDecimal(0))
  }

}
