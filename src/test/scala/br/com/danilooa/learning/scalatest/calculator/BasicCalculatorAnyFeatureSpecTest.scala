package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

class BasicCalculatorAnyFeatureSpecTest extends AnyFeatureSpec with GivenWhenThen {

  Feature("BasicCalculator") {
    Scenario("A BasicCalculator is read to use") {
      Given("numbers to multiple")
      When("one of them is 0")
      val zero = BigDecimal(0)
      Then("the result must be 0")
      assert(BasicCalculator.multiple(BigDecimal(10), zero) == BigDecimal(0))
    }
  }
}
