package com.vassant.kata.domain

import java.time.LocalDateTime

sealed class Operation {
    abstract val date: LocalDateTime
    abstract val amount: Amount
}

data class Withdraw(override val date: LocalDateTime, override val amount: Amount) : Operation()
data class Deposit(override val date: LocalDateTime, override val amount: Amount) : Operation()
