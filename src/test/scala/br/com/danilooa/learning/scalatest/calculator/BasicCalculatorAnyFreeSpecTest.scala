package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.funspec.AnyFunSpec

class BasicCalculatorAnyFreeSpecTest extends AnyFreeSpec {

  "Any number" - {
    "times 0" - {
      "should be 0" in {
        val result = BasicCalculator.multiple(BigDecimal(10), BigDecimal(0))
        assert(result == 0)
      }
    }
  }

}
