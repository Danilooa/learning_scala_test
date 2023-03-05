package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.refspec.RefSpecLike

class BasicCalculatorRefSpecTest extends RefSpecLike {

  object `A number` {
    object `when multipled by 0` {
      def `should be 0 `: Unit = {
        assert(BasicCalculator.multiple(19, 0) == 1)
      }
    }
  }

}
