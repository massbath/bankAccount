package com.vassant.kata.domain

data class Amount(val value: Int) {
    init {
        if (value < 0) throw NegativeAmountNotAllowedException()
    }
}