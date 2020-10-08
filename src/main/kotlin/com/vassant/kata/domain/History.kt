package com.vassant.kata.domain

data class History(var operation: Collection<Operation>) {
    init {
        operation = operation.sortedBy { it.date }
    }
}
