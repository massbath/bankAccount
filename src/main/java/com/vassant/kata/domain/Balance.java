package com.vassant.kata.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class Balance {

    private final int value;

    static Balance of(int value) {
        return new Balance(value);
    }

    void add(Amount amount) {
        Balance.of(value + amount.getValue());
    }
}
