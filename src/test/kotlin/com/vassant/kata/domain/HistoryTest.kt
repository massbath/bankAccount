package com.vassant.kata.domain

import com.vassant.kata.domain.Balance.Companion.of
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
                .containsExactly(Tuple(TODAY, of(100)),
                        Tuple(TOMORROW, of(200)),
                        Tuple(NEXT_WEEK, of(300))
                )
    }

    private fun aOperationAt(TOMORROW: LocalDateTime): Operation {
        return Deposit(TOMORROW, Amount(100), of(0))
    }
}