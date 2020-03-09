package com.vassant.kata.domain.adapters;

import com.vassant.kata.domain.ports.Clock;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

public class FakeClock implements Clock {
    private final LocalDateTime startDateToReturn;
    private final Collection<LocalDateTime> datesReturned = new LinkedList<>();

    public FakeClock(LocalDateTime startDateToReturn) {
        this.startDateToReturn = startDateToReturn;
    }

    public LocalDateTime getActualDate() {
        final LocalDateTime dateToReturn = this.startDateToReturn.plusDays(datesReturned.size());
        this.datesReturned.add(dateToReturn);
        return dateToReturn;

    }
}
