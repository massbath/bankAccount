package com.vassant.kata.domain

import com.vassant.kata.domain.ports.BankAccountOperations
import com.vassant.kata.domain.ports.Clock
import com.vassant.kata.domain.ports.Operations

class BankAccount(private val operations: Operations, private val clock: Clock) : BankAccountOperations {
    override fun deposit(amount: Amount) = operations save Deposit(clock.actualDate(), amount)

    override fun withdraw(amount: Amount) = operations save Withdraw(clock.actualDate(), amount)

    override fun balance(): Balance = operations.all().fold(Balance(0)) { balance, operation -> balance apply operation }

    override fun history(): History = History(operations.all())
}