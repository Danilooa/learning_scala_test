package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable.ListBuffer

class GetFixtureExample extends AnyFlatSpec {
  def fixture = {
    new {
      val builder = new StringBuilder("ScalaTest is ");
      val buffer = new ListBuffer[String]
    }
  }

  "Test" should "be easy" in {
    //Fixture created
    val f = fixture
    f.builder.append("easy!")
    assert(f.builder.toString() === "ScalaTest is easy!")
    assert(f.buffer.isEmpty)
    f.buffer += "sweet"
    //None after operation needed
  }

  it should "be fun" in {
    //Fixture created again
    val f = fixture
    f.builder.append("fun!")
    assert(f.builder.toString() === "ScalaTest is fun!")
    assert(f.buffer.isEmpty)
    //None after operation needed
  }
}
