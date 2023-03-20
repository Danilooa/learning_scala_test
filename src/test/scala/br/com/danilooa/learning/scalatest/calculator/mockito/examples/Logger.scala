package br.com.danilooa.learning.scalatest.calculator.mockito.examples

import java.time.LocalDateTime

trait Logger {
  def logTime(txnRef: String, dateTime: LocalDateTime): Unit
}
