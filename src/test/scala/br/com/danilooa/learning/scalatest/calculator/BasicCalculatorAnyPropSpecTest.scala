package br.com.danilooa.learning.scalatest.calculator

import org.scalacheck.Gen
import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

//ScalaCheckPropertyChecks should be mixed or the elements should be imported
class BasicCalculatorAnyPropSpecTest extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {

  property("Any number times 0 should be 0") {

    //forAll needs a table or a Generator that produces a table
    //Gen.listOf(Gen.choose(0, 100), 0) is this case is a generator
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
