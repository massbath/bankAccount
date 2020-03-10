package com.vassant.kata.domain;

import lombok.Value;

@Value
public final class Amount {
    private final int value;

    private Amount(int value) {
        this.value = value;
    }

    static Amount of(int value) {
        if(value < 0)
            throw new NegativeAmountNotAllowedException();
        return new Amount(value);
    }
}
