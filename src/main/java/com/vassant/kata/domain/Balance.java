package com.vassant.kata.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Balance {

    private int value;

    static Balance of(int value) {
        return new Balance(value);
    }

    void add(Amount amount) {
        value += amount.getValue();
    }

    void remove(Amount amount) {
        value -= amount.getValue();
    }

    boolean hasEnoughSavings(Amount amount) {
        return value >= amount.getValue();
    }
}
