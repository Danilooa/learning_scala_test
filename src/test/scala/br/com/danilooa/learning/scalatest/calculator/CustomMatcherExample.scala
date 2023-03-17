package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.matchers.MatchResult
import org.scalatest.matchers.Matcher

trait CustomMatcherExample {

  class CanSplitStringInTwoEqualParts(numberOfParts: Int) extends Matcher[String] {

    def apply(string: String) = {
      val value = string.size % numberOfParts
      MatchResult(
        value == 0,
        s"The '$string' has the size of ${string.size} and cannot be split in two ",
        s"The size of the string '$string' is ${string.size} and can be split in two"
      )
    }
  }

  def splitStringInTwoEqualParts(numberOfParts: Int) = new CanSplitStringInTwoEqualParts(numberOfParts)


}

object CustomMatcherExample extends CustomMatcherExample
