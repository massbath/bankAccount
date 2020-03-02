package com.vassant.kata.domain.ports;

import java.time.LocalDate;

public interface Clock {
    LocalDate getActualDate();
}
