package com.vassant.kata.domain

import com.vassant.kata.domain.ports.Clock
import com.vassant.kata.domain.ports.Operations
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class BankAccountTest {
    @Mock
    private lateinit var operations: Operations

    @Mock
    private lateinit var clock: Clock

    @InjectMocks
    private lateinit var bankAccount: BankAccount

    companion object {
        private val AN_AMOUNT_OF_100 = Amount(100)
        private val NOW = LocalDateTime.now()
    }

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.lenient().`when`(clock.actualDate()).thenReturn(NOW)
    }

    @Nested
    inner class Deposit {
        @Test
        fun `should be save in operations`() {
            val operationToSaveExpected = Deposit(NOW, AN_AMOUNT_OF_100)

            bankAccount.deposit(AN_AMOUNT_OF_100)

            verify(operations).save(operationToSaveExpected)
        }
    }

    @Nested
    inner class Withdraw {

        @Test
        fun `should be save in operations`() {
            Mockito.`when`(operations.all()).thenReturn(listOf(Deposit(NOW, AN_AMOUNT_OF_100)))
            val operationToSaveExpected = Withdraw(NOW, AN_AMOUNT_OF_100)

            bankAccount.withdraw(AN_AMOUNT_OF_100)

            verify(operations).save(operationToSaveExpected)
        }
    }

}