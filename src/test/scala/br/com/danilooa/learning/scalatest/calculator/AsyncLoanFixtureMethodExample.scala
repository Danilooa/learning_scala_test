package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.Assertion
import org.scalatest.flatspec.AsyncFlatSpec

import java.util.Date
import scala.concurrent.Future

class AsyncLoanFixtureMethodExample extends AsyncFlatSpec {

  def setDataBase() = {
    val dataBaseName = "dataBaseName"
    println(s"Created database $dataBaseName")
    dataBaseName
  }

  def dropDataBase(dataBaseName: String) = {
    println(s"Dropped database $dataBaseName")
  }

  //fixture with args
  def withDataBase(test: (String) => Future[Assertion]): Future[Assertion] = {
    val dataBaseName = setDataBase()
    val return_ = test(dataBaseName)
    dropDataBase(dataBaseName)
    return_
  }

  //another fixture with args
  def withLog(test: (Long, String) => Future[Assertion]): Future[Assertion] = {
    val start = System.currentTimeMillis()
    val testStartedAt = s"The started at ${new Date(start)}"
    val return_ = test(start, testStartedAt)
    val end = System.currentTimeMillis()
    println(s"The finished at ${new Date(end)}")
    return_
  }

  "It" should "test a simple assertion with fixture" in withDataBase { dataBase =>
    println(s"Using database $dataBase")
    Future {
      1 + 2
    }.map(sum => assert(sum == 3))
  }

  "It" should "test if an exception was thrown" in withDataBase { dataBase =>
    println(s"Using database $dataBase")
    val futureThatThrowsException = Future {
      1 / 0
    }

    recoverToSucceededIf[ArithmeticException] {
      futureThatThrowsException
    }
  }

  "It" should "concat fixture using the loan pattern" in withDataBase { dataBase =>
    withLog { (start, message) =>
      println(s"$start Using database $dataBase message '$message'")
      Future {
        1 + 2
      }.map(sum => assert(sum == 3))
    }
  }

}

