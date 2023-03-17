package br.com.danilooa.learning.scalatest.calculator

import org.scalactic.Equality
import org.scalatest.Inside.inside
import org.scalatest.Inspectors.forAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import br.com.danilooa.learning.scalatest.calculator.CustomMatcherExample.splitStringInTwoEqualParts
import org.scalatest.matchers.MatchResult
import org.scalatest.matchers.Matcher

import scala.reflect.io.File

class MatchersExamples extends AnyFlatSpec with Matchers {

  /** **************************************************************************
   * Example 1: Overriding Equality[T]
   *
   * You can explicitly supply Equality implementations as curried parameters
   * and using the DSL equal(value) to customize the way things are compared.
   * It doesn't work with the DSL be(value)
   *
   * This example will compared Strings ignoring cases.
   *
   * The following code implements Equality[String] to ignore cases
   * ************************************************************************* */

  object lowerCased extends Equality[String] {
    override def areEqual(a: String, b: Any): Boolean = {
      if (a == null) return false

      val (isParameterBString, bAsString) = b match {
        case _: String => (true, b.asInstanceOf[String])
        case _ => (false, "")
      }

      if (!isParameterBString) return false
      a.toLowerCase() == bAsString.toLowerCase()
    }
  }

  /** **************************************************************************
   * Example 1
   * Here, the lowerCased parameter is explicity passed as a parameter
   * ************************************************************************* */
  "String" should "ignore cases when compared" in {
    "AbCdEf" should (equal("abcdef")(lowerCased))
  }

  /** **************************************************************************
   * Tip 1
   * Instead == the comparator !== ensures type constraint.
   * ************************************************************************* */

  /** **************************************************************************
   * Example 2
   *
   * It is possible to validate no arg methods that return booleans using
   * Object shouldBe Symbol("nameOfTheMethod")
   * ************************************************************************* */
  object AnyHavingMethodIfNoArgsReturningBoolean {
    def returnsBoolean = true
  }

  "DSL" should "be able to validate boolean methods" in {
    AnyHavingMethodIfNoArgsReturningBoolean shouldBe Symbol("returnsBoolean")
  }

  /** **************************************************************************
   * Example 3
   *
   * You can compare the type of an Instance using instnace shouldBe a[Type]
   * ************************************************************************* */
  "DSL" should "validate type" in {
    val file: File = File("url")
    file shouldBe a[File]
  }

  /** **************************************************************************
   * Tip 2
   *
   * Many matchers don't support iterators. So, in order to test them,
   * convert them to a Seq
   * ************************************************************************* */

  /** **************************************************************************
   * Example 4
   *
   * You can use the Inspectors syntax with matchers as well as assertions.
   * If you have a multi-dimensional collection, such as a list of lists,
   * using Inspectors is your best option.
   *
   * There are many inspectors, please check the documentation.
   * ************************************************************************* */
  "These inspector example" should "be clean" in {
    val yss =
      List(
        List(1, 2, 3),
        List(1, 2, 3),
        List(1, 2, 3)
      )

    forAll(yss) { ys =>
      forAll(ys) { y => y should be > 0 }
    }
  }

  /** **************************************************************************
   * Example 5
   *
   * Matchers offer logical operators
   * ************************************************************************* */
  "Matchers" should "support logical operators" in {
    val vec = Vector(
      2,
      4,
      6
    )
    vec should (contain(2) and not contain (5))
  }

  case class WithFields(field1: Int, field2: String, field3: BigDecimal) {
    def oneTwo() = s"$field1 $field2"
  }

  /** **************************************************************************
   * Example 6
   *
   * It possible to test using the name of fields or function throw reflection,
   * to do so, use the matcher have.
   * ************************************************************************* */
  "It" should "possible to check arbitrary properties" in {
    val withFields = WithFields(1, "My Name", 0)
    withFields should have(
      Symbol("field1")(1)
    )

    val manyValues = Vector(1, 3, 4, 4, 32, 65)

    manyValues should have {
      size(6)
    }

  }

  /** **************************************************************************
   * Example 7
   *
   * Match expressions can be used.
   * ************************************************************************* */
  "it" should "be possible to test using match expression" in {
    val withFields = WithFields(1, "My Name", 0)

    inside(withFields) {
      case WithFields(field1, _, _) =>
        field1 should be > 0
    }
  }


  /** **************************************************************************
   * Example 8
   *
   * It is possible to check if a exception was thrown.
   * There are more ways to check those exceptions,
   * check the official documentation
   * ************************************************************************* */
  "it" should "do something" in {
    val s = "1"
    an [IndexOutOfBoundsException] should be thrownBy s.charAt(0)
  }
}

