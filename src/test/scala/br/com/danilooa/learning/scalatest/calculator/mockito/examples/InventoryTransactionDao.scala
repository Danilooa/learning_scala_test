package br.com.danilooa.learning.scalatest.calculator.mockito.examples

import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InventoryTransactionDao {

  def getAll() = {
    Future {
      Seq(new InventoryTransaction())
    }
  }

  def save(transaction: InventoryTransaction) = {
    Future.successful(transaction)
  }

}
