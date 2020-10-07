package com.vassant.kata.domain

import com.vassant.kata.domain.ports.BankAccountOperations
import com.vassant.kata.domain.ports.Clock
import com.vassant.kata.domain.ports.Operations

class BankAccount(private val operations: Operations, private val clock: Clock) : BankAccountOperations {
    override fun deposit(amount: Amount) = saveOperation(amount, OperationType.DEPOSIT, balance().deposit(amount))

    override fun withdraw(amount: Amount) {
        val balance = balance()
        if (!balance.hasEnoughSavings(amount)) throw NotEnoughSavingsException()
        saveOperation(amount, OperationType.WITHDRAW, balance.withdraw(amount))
    }

    override fun balance(): Balance = operations.all().maxByOrNull { it.date }?.balance ?: Balance(0)

    override fun history(): History = History(operations.all())

    private fun saveOperation(amount: Amount, deposit: OperationType, balance: Balance) =
            operations.save(Operation.builder()
                    .operationType(deposit)
                    .date(clock.actualDate)
                    .amount(amount)
                    .balance(balance)
                    .build())
}