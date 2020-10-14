package com.vassant.kata.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple
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

        assertThat(history.lines).extracting("operation.date", "balance")
                .containsExactly(
                        Tuple(TODAY, Balance(100)),
                        Tuple(TOMORROW, Balance(200)),
                        Tuple(NEXT_WEEK, Balance(300))
                )
    }

    private fun aOperationAt(dateOperation: LocalDateTime): Operation {
        return Deposit(dateOperation, Amount(100), Balance(0))
    }
}