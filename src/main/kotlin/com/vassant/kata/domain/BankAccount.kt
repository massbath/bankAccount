package com.vassant.kata.domain

import com.vassant.kata.domain.ports.BankAccountOperations
import com.vassant.kata.domain.ports.Clock
import com.vassant.kata.domain.ports.Operations

class BankAccount(private val operations: Operations, private val clock: Clock) : BankAccountOperations {
    override fun deposit(amount: Amount) = addOperation(Deposit(clock.actualDate(), amount, balance() deposit amount))

    override fun withdraw(amount: Amount) {
        val balance = balance()
        if (!balance.hasEnoughSavings(amount)) throw NotEnoughSavingsException()
        addOperation(Withdraw(clock.actualDate(), amount, balance withdraw amount))
    }

    private fun addOperation(operation: Operation) {
        operations add operation
    }

    override fun balance(): Balance = operations.all().fold(Balance(0)) { balance, operation -> balance apply operation }


    override fun history(): History = History(operations.all())

}