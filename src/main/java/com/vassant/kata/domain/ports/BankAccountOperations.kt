package com.vassant.kata.domain.ports

import com.vassant.kata.domain.Amount
import com.vassant.kata.domain.Balance
import com.vassant.kata.domain.History

interface BankAccountOperations {
    fun deposit(amount: Amount)
    fun withdraw(amount: Amount)
    fun balance(): Balance
    fun history(): History
}