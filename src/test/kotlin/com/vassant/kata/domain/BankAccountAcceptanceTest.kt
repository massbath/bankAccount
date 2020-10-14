package com.vassant.kata.domain

import com.vassant.kata.domain.adapters.FakeClock
import com.vassant.kata.domain.adapters.OperationsInMemory
import com.vassant.kata.domain.ports.BankAccountOperations
import com.vassant.kata.domain.ports.Clock
import com.vassant.kata.domain.ports.Operations
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class BankAccountAcceptanceTest {
    private val TODAY = LocalDateTime.now()
    private val fakeClock: Clock = FakeClock(TODAY)
    private val operations: Operations = OperationsInMemory()
    private val aAmountOf100 = Amount(100)

    @Test
    fun `all operations on account should be searchable`() {
        val bankAccount: BankAccountOperations = BankAccount(operations, fakeClock)
        val historyExpected = setOf(
                HistoryLine(Deposit(TODAY, aAmountOf100), Balance(100)),
                HistoryLine(Deposit(TODAY.plusDays(1), aAmountOf100), Balance(200)),
                HistoryLine(Withdraw(TODAY.plusDays(2), aAmountOf100), Balance(100)),
                HistoryLine(Deposit(TODAY.plusDays(3), aAmountOf100), Balance(200)))

        bankAccount.deposit(aAmountOf100)
        bankAccount.deposit(aAmountOf100)
        bankAccount.withdraw(aAmountOf100)
        bankAccount.deposit(aAmountOf100)

        val history = bankAccount.history()
        Assertions.assertThat(history.lines).isEqualTo(historyExpected)

    }

}