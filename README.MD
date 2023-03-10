# Learning Scalatest

## Styles

Represent only different manners of writing a test, there aren't differences on how the tests work<br>
just the way of writings changes.

### [FunSuite](src/test/scala/br/com/danilooa/learning/scalatest/calculator/BasicCalculatorFunSuitTest.scala)

Most basic style of test, ideal for unit testing

### [FlatSpec](src/test/scala/br/com/danilooa/learning/scalatest/calculator/BasicCalculatorFlatSpecTest.scala)

BDD testing style

### [FunSpec](src/test/scala/br/com/danilooa/learning/scalatest/calculator/BasicCalculatorAnyFunSpecTest.scala)

BDD testing style based on Ruby's RSpec

### [FunSuiteSpec](src/test/scala/br/com/danilooa/learning/scalatest/calculator/BasicCalculatorFunSuiteTest.scala)

BDD testing style that gives total freedom on how the specification test should be writing.<br>
Since it doesn't follow patterns, it is indicated for teams experienced with BDD test that can<br>
decide by themselves how to write the tests.

### [PropSpec](src/test/scala/br/com/danilooa/learning/scalatest/calculator/BasicCalculatorAnyPropSpecTest.scala)

The AnyPropSpec style is perfect for teams that want to write tests exclusively in terms of property checks;<br>
also a good choice for writing the occasional test matrix when a different style trait is chosen<br>
as the main unit testing style.

### [FeatureSpec](src/test/scala/br/com/danilooa/learning/scalatest/calculator/BasicCalculatorAnyFeatureSpecTest.scala)

The FeatureSpec style is primarily intended for acceptance testing,<br>
including facilitating the process of programmers working alongside non-programmers to define the acceptance requirements.

### [RefSpec](src/test/scala/br/com/danilooa/learning/scalatest/calculator/BasicCalculatorRefSpecTest.scala)

The RefSpec style allows you to define tests as methods, which saves one function literal per test compared to style classes that represent tests as functions.

## Base Classes

Are just classes that set features that many others tests will set as well.<br>
Then, it is better to set those features in the BaseClass and then inherit it when needed.

## Fixtures

Code that performs actions needed before or/and after many tests,<br>
they are a way to avoid code repetition among tests.

### [Get-fixture](src/test/scala/br/com/danilooa/learning/scalatest/calculator/GetFixtureExample.scala)

If you need to create the same mutable fixture objects in multiple tests,<br>
and don't need to clean them up after using them get-fixture is the best choice.

### [Fixture-context objects](src/test/scala/br/com/danilooa/learning/scalatest/calculator/FixtureContextObjectsExample.scala)

An alternate technique that is especially useful when different tests need different combinations of fixture objects<br>
is to define the fixture objects as instance variables of fixture-context objects<br>
whose instantiation forms the body of tests. Like get-fixture methods,<br>
fixture-context objects are only appropriate if you don't need to clean up the fixtures after using them.

### [WithFixture(NoArgTest)](src/test/scala/br/com/danilooa/learning/scalatest/calculator/WithFixtureNoArgTestExample.scala)

Performs side-effects at the beginning or end of a test but doesn't actually pass any fixture objects into the test.

### [Loan-fixture methods](src/test/scala/br/com/danilooa/learning/scalatest/calculator/LoanFixtureMethodExampleSpec.scala)

Performs side-effects at the beginning or end of a test and passes any fixture object into the test.

### [WithFixtureOneArgTest](src/test/scala/br/com/danilooa/learning/scalatest/calculator/WithFixtureOneArgTextExample.scala)

Use when you want to pass the same fixture object or objects as a parameter into all or most tests.<br>
If all or most tests need the same fixture, you can avoid some of the boilerplate of the loan-fixture method approach.

### Mixing in BeforeAndAfter

- Fixture runs inside the tests (At the beginning and end)<br>
then if a fixture throws an Exception it will be reported as a test failure
- BeforeAndAfter runs, as the name suggests, before and after the tests outside them.<br>
Consequently, if a BeforeAndAfter throws an exception the entire suite aborts
- The test will run after and before all the tests in the suite
- The BeforeAndAfter trait can communicate with the tests only by side effects<br>
what prevents parallel executions
- Mixing ParallelTestExecution with BeforeAndAfter runs the tests in their own contexts<br>
preventing the side effects to mess up the tests and them they can run in parallel
- BeforeAndAfter is easy to use but doesn't support stacking

### Composing fixtures by stacking traits

- Stacking is technic to combine fixture traits to solve tests requirements,<br>
they are used because in some tests there is the need to combine different fixtures to a set of tests
- The following example teaches how to apply this technic:

```
package org.scalatest.examples.flatspec.composingwithfixture

import org.scalatest._
import collection.mutable.ListBuffer

//Implement each trait extending SuiteMixin
//Implementing the first trait
trait Builder extends SuiteMixin { this: Suite =>

  val builder = new StringBuilder

  abstract override def withFixture(test: NoArgTest) = {
    builder.append("ScalaTest is ")
    //calls the methdd super.withFixture(test) to stack the fixtures
    try super.withFixture(test) // To be stackable, must call super.withFixture
    finally builder.clear()
  }
}

//Implementing the second trait
trait Buffer extends SuiteMixin { this: Suite =>

  val buffer = new ListBuffer[String]

  abstract override def withFixture(test: NoArgTest) = {
    //calls the methdd super.withFixture(test) to stack the fixtures
    try super.withFixture(test) // To be stackable, must call super.withFixture
    finally buffer.clear()
  }
}

//The trait will be stacked in the order they are mixed, in this case Buffer will be stacked over Builder
class ExampleSpec extends AnyFlatSpec with Builder with Buffer {

  "Testing" should "be easy" in {
    builder.append("easy!")
    assert(builder.toString === "ScalaTest is easy!")
    assert(buffer.isEmpty)
    buffer += "sweet"
  }

  it should "be fun" in {
    builder.append("fun!")
    assert(builder.toString === "ScalaTest is fun!")
    assert(buffer.isEmpty)
    buffer += "clear"
  }
}
```
- The same technic could be applíed with BeforeAndAfter

## [Sharing tests](src/test/scala/br/com/danilooa/learning/scalatest/calculator/SharingTestsExample.scala)

You can share tests placing them in a stack and then calling behave followed by the test function.