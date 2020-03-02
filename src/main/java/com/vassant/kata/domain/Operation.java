package com.vassant.kata.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public final class Operation {
    private LocalDate date;
    private OperationType operationType;
    private Amount amount;
    private Balance balance;
}
