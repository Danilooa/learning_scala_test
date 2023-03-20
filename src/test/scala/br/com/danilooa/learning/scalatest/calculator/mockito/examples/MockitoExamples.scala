package br.com.danilooa.learning.scalatest.calculator.mockito.examples

import org.mockito.ArgumentCaptor
import org.mockito.Mockito.{spy, times, verify, when}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar.mock

import java.time.LocalDateTime
import scala.collection.mutable
import scala.concurrent.Future
import scala.jdk.CollectionConverters._

class MockitoExamples extends AnyFlatSpec with Matchers {
  "Mockito" should "mock a method with no parameters" in {
    val txns: Seq[InventoryTransaction] = Nil
    val dao = mock[InventoryTransactionDao]
    val futureExpected = Future.successful(txns)
    when(dao.getAll()).thenReturn(futureExpected)
    val service = new InventoryService(dao, new Logger {
      override def logTime(txnRef: String, dateTime: LocalDateTime): Unit = {}
    })
    val futureActual = service.getAll()
    futureActual should equal(futureExpected)
  }

  "Mockito" should "mock a method with parameters" in {
    val transaction = new InventoryTransaction
    val dao = mock[InventoryTransactionDao]
    val success = Future.successful(transaction)
    when(dao.save(transaction)).thenReturn(success)
    val service = new InventoryService(dao, new Logger {
      override def logTime(txnRef: String, dateTime: LocalDateTime): Unit = {}
    })
    val futureActual = service.save(transaction)
    futureActual should equal(success)
  }

  "Mockito" should "count method calls" in {
    val transaction = new InventoryTransaction
    val dao = mock[InventoryTransactionDao]
    val service = new InventoryService(dao, new Logger {
      override def logTime(txnRef: String, dateTime: LocalDateTime): Unit = {}
    })
    service.save(transaction)
    service.save(transaction)
    verify(dao, times(2)).save(transaction)
  }

  "Mockito" should "verify method arguments" in {
    val beginning = LocalDateTime.now()

    val transaction = new InventoryTransaction
    val dao = mock[InventoryTransactionDao]
    val mockLogger = mock[Logger]
    val service = new InventoryService(dao, mockLogger)
    service.save(transaction)
    service.getAll
    val refCapture = ArgumentCaptor.forClass(classOf[String])
    val refLocalDateTime: ArgumentCaptor[LocalDateTime] = ArgumentCaptor.forClass(classOf[LocalDateTime])

    val end = LocalDateTime.now()

    verify(mockLogger, times(2)).logTime(refCapture.capture(), refLocalDateTime.capture())
    refCapture.getAllValues.asScala should be(mutable.Buffer("InventoryService.save", "InventoryService.getAll"))

    all(refLocalDateTime.getAllValues) should (be >= beginning and be <= end)
  }

  "Mockito" should "spy an object" in {
    val transaction = new InventoryTransaction
    val expectedTransaction = Future.successful(transaction)

    val mockDao = mock[InventoryTransactionDao]
    when(mockDao.save(transaction)).thenReturn(expectedTransaction)

    val service = new InventoryService(mockDao, new Logger {
      override def logTime(txnRef: String, dateTime: LocalDateTime): Unit = {}
    })
    val spiedService = spy(service)

    val txns: Seq[InventoryTransaction] = Nil
    val futureExpected = Future.successful(txns)
    when(spiedService.getAll()).thenReturn(futureExpected)
    val futureActual = spiedService.getAll()

    val actualTransaction = spiedService.save(transaction)
    expectedTransaction should equal(actualTransaction)

    futureExpected should equal(futureActual)
  }

}

