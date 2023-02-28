package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funspec.AnyFunSpec

class BasicCalculatorAnyFunSpecTest extends AnyFunSpec {

  describe("Any number") {
    describe("times 0") {
      it("should be 0") {
        val result = BasicCalculator.multiple(BigDecimal(1), BigDecimal(0))
        assert(result == 0)
      }
    }
  }

}
