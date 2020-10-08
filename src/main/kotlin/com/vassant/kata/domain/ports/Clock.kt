package com.vassant.kata.domain.ports

import java.time.LocalDateTime

interface Clock {
    fun actualDate(): LocalDateTime
}