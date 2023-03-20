package br.com.danilooa.learning.scalatest.calculator.mockito.examples

import java.time.LocalDateTime

class InventoryService(
                        dao: InventoryTransactionDao,
                        logger: Logger
                      ) {

  def getAll() = {
    val future = dao.getAll()
    logger.logTime("InventoryService.getAll", LocalDateTime.now())
    future
  }

  def save(transaction: InventoryTransaction) = {
    val eventualTransaction = dao.save(transaction)
    logger.logTime("InventoryService.save", LocalDateTime.now())
    eventualTransaction
  }

}
