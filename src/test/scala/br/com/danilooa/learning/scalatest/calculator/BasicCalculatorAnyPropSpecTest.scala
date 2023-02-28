package br.com.danilooa.learning.scalatest.calculator

import org.scalacheck.Gen
import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class BasicCalculatorAnyPropSpecTest extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {

  property("Any number times 0 should be 0") {
    forAll(Gen.listOf(Gen.choose(0, 100), 0)) {
      list => {
        list.foreach(input => {
          val result = BasicCalculator.multiple(BigDecimal(input._1.sample.getOrElse(0)), input._2)
          assert(result == BigDecimal(0))
        })
      }
    }

  }
}
