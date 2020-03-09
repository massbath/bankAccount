package com.vassant.kata.domain.ports;

import java.time.LocalDateTime;

public interface Clock {
    LocalDateTime getActualDate();
}
