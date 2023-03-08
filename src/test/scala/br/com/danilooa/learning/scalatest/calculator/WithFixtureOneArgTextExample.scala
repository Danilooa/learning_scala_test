package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.flatspec.FixtureAnyFlatSpec

class WithFixtureOneArgTextExample extends FixtureAnyFlatSpec {

  case class User(id: Long, name: String)

  case class FixtureParam(user: User)

  override protected def withFixture(test: OneArgTest) = {
    try {
      val user = User(100, "Adrian")
      println(s"--> Creating database ")
      println(s"--> Inserting $user in the database")
      withFixture(test.toNoArgTest(FixtureParam(user)))
    }
    finally {
      println(s"--> Droping the database ")
    }
  }

  "The fixture" should "be call before and after" in { f =>
    println(s"Testing ... the using is ${f.user}")
    succeed
  }
}
