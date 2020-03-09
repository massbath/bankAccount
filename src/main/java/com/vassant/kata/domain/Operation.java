package com.vassant.kata.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public final class Operation {
    private LocalDateTime date;
    private OperationType operationType;
    private Amount amount;
    private Balance balance;
}
