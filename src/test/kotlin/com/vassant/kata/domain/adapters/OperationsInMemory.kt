package com.vassant.kata.domain.adapters

import com.vassant.kata.domain.Operation
import com.vassant.kata.domain.ports.Operations
import java.util.*

class OperationsInMemory : Operations {
    private val operations: MutableCollection<Operation> = ArrayList()

    override fun all(): Collection<Operation> = operations

    override infix fun add(operation: Operation) {
        operations.add(operation)
    }
}