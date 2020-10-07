package com.vassant.kata.domain

data class History(var operations: Collection<Operation>) {
    init {
        operations = operations.sortedBy { it.date }
    }
}
