package com.vassant.kata.domain.adapters

import com.vassant.kata.domain.ports.Clock
import java.time.LocalDateTime
import java.util.*

class FakeClock(private val startDateToReturn: LocalDateTime) : Clock {
    private val datesReturned: MutableCollection<LocalDateTime> = LinkedList()

    override fun actualDate(): LocalDateTime {
        val dateToReturn = startDateToReturn.plusDays(datesReturned.size.toLong())
        datesReturned.add(dateToReturn)
        return dateToReturn
    }
}