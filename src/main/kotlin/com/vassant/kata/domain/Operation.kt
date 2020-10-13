package com.vassant.kata.domain

import java.time.LocalDateTime

sealed class Operation {
    abstract val date: LocalDateTime
    abstract val amount: Amount
    abstract val balance: Balance
}

data class Withdraw(override val date: LocalDateTime, override val amount: Amount, override val balance: Balance) : Operation()
data class Deposit(override val date: LocalDateTime, override val amount: Amount, override val balance: Balance) : Operation()
