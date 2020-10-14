package com.vassant.kata.domain.ports

import com.vassant.kata.domain.Operation

interface Operations {
    fun all(): Collection<Operation>
    infix fun save(operation: Operation)
}