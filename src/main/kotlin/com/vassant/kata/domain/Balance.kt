package com.vassant.kata.domain

data class Balance(private val value: Int) {
    infix fun deposit(amount: Amount): Balance = Balance(value + amount)

    infix fun withdraw(amount: Amount): Balance = Balance(value - amount)

    fun hasEnoughSavings(amount: Amount): Boolean = value >= amount.value

    infix fun apply(operation: Operation): Balance =
            when (operation) {
                is Withdraw -> this withdraw operation.amount
                is Deposit -> this deposit operation.amount
            }
}

operator fun Int.plus(amount: Amount): Int = this + amount.value
operator fun Int.minus(amount: Amount): Int = this - amount.value