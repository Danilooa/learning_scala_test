package br.com.danilooa.learning.scalatest.calculator

import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID.randomUUID

case class DataBaseDetails(host: String,
                           name: String,
                           user: String,
                           password: String,
                           creationTime: Long)

object DbServer { // Simulating a database server

  def createDb(): DataBaseDetails = {
    val databaseName = s"MY_DATABASE_${randomUUID.toString}"
    println(s"Creating the volatile database $databaseName")
    println("Versioning the database...")

    DataBaseDetails(
      "10.10.10.1",
      databaseName,
      "root",
      "123456",
      System.currentTimeMillis()
    )
  }

  def removeDb(dataBaseDetails: DataBaseDetails) {
    println(s"Removing database ${dataBaseDetails.name}")
  }
}

class LoanFixtureMethodExampleSpec extends AnyFlatSpec {

  def withDatabase(testCode: (DataBaseDetails) => Any) {
    // setting up the fixture
    val databaseDetails = DbServer.createDb()
    try {
      testCode(databaseDetails) // "loan" the fixture to the test
    }
    finally {
      // clean up the fixture
      DbServer.removeDb(databaseDetails)
    }
  }

  // This test needs the database fixture
  "Test code" should "be readable" in withDatabase { (dataBaseDetails) =>
    println(s"----> Actually testing <---- Inserting rows in the database ${dataBaseDetails.name}")
    assert(!dataBaseDetails.name.isEmpty)
  }

}
