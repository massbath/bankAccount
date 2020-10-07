package com.vassant.kata.domain

data class Balance(private val value: Int) {
    fun deposit(amount: Amount): Balance = Balance(value + amount.value)

    fun withdraw(amount: Amount): Balance = Balance(value - amount.value)

    fun hasEnoughSavings(amount: Amount): Boolean = value >= amount.value

    companion object {
        @JvmStatic
        fun of(value: Int): Balance {
            return Balance(value)
        }
    }
}