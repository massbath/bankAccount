package com.vassant.kata.domain;

import lombok.Value;

@Value
public final class Amount {
    private final int value;

    static Amount of(int value) {
        return new Amount(value);
    }
}
