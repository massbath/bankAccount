package com.vassant.kata.domain

import com.vassant.kata.domain.Balance.Companion.of
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class HistoryTest {

    @Test
    fun `should sort operations by date`() {
        val TODAY = LocalDateTime.now()
        val TOMORROW = TODAY.plusDays(1)
        val NEXT_WEEK = TODAY.plusWeeks(1)
        val operation = Arrays.asList(aOperationAt(TOMORROW), aOperationAt(TODAY), aOperationAt(NEXT_WEEK))

        val history = History(operation)

        Assertions.assertThat(history.operation).extracting("date").containsExactly(TODAY, TOMORROW, NEXT_WEEK)
    }

    private fun aOperationAt(TOMORROW: LocalDateTime): Operation {
        return Operation(TOMORROW, OperationType.DEPOSIT, Amount(100), of(0))
    }
}