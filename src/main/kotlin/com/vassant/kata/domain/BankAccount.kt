package com.vassant.kata.domain

import com.vassant.kata.domain.ports.BankAccountOperations
import com.vassant.kata.domain.ports.Clock
import com.vassant.kata.domain.ports.Operations

class BankAccount(private val operations: Operations, private val clock: Clock) : BankAccountOperations {
    override fun deposit(amount: Amount) = addOperation(amount, OperationType.DEPOSIT, balance() deposit amount)

    override fun withdraw(amount: Amount) {
        val balance = balance()
        if (!balance.hasEnoughSavings(amount)) throw NotEnoughSavingsException()
        addOperation(amount, OperationType.WITHDRAW, balance withdraw amount)
    }

    override fun balance(): Balance = operations.all().maxByOrNull { it.date }?.balance ?: Balance(0)

    override fun history(): History = History(operations.all())

    private fun addOperation(amount: Amount, deposit: OperationType, balance: Balance) = operations add Operation(
            operationType = deposit,
            amount = amount,
            balance = balance,
            date = clock.actualDate())
}