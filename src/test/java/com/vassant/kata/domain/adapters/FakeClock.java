package com.vassant.kata.domain.adapters;

import com.vassant.kata.domain.Clock;

import java.time.LocalDate;

public class FakeClock implements Clock {
    private final LocalDate dateToReturn;

    public FakeClock(LocalDate dateToReturn) {
        this.dateToReturn = dateToReturn;
    }

    public LocalDate getActualDate() {
        return dateToReturn;
    }
}
