package com.vassant.kata.domain

import com.vassant.kata.domain.Balance.Companion.of
import com.vassant.kata.domain.adapters.FakeClock
import com.vassant.kata.domain.adapters.OperationsInMemory
import com.vassant.kata.domain.ports.BankAccountOperations
import com.vassant.kata.domain.ports.Clock
import com.vassant.kata.domain.ports.Operations
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class BankAccountAcceptanceTest {
    private val TODAY = LocalDateTime.now()
    private val fakeClock: Clock = FakeClock(TODAY)
    private val operations: Operations = OperationsInMemory()
    private val aAmountOf100 = Amount(100)

    @Test
    fun `all operations on account should be searchable`() {
        val bankAccount: BankAccountOperations = BankAccount(operations, fakeClock)
        val historyExpected = History(Arrays.asList(
                aOperation(OperationType.DEPOSIT, TODAY, 100),
                aOperation(OperationType.DEPOSIT, TODAY.plusDays(1), 200),
                aOperation(OperationType.WITHDRAW, TODAY.plusDays(2), 100),
                aOperation(OperationType.DEPOSIT, TODAY.plusDays(3), 200)))

        bankAccount.deposit(aAmountOf100)
        bankAccount.deposit(aAmountOf100)
        bankAccount.withdraw(aAmountOf100)
        bankAccount.deposit(aAmountOf100)

        Assertions.assertThat(bankAccount.history()).isEqualTo(historyExpected)
    }

    private fun aOperation(operationType: OperationType, dateOperation: LocalDateTime, balance: Int): Operation =
            Operation(dateOperation, operationType, aAmountOf100, of(balance))

}