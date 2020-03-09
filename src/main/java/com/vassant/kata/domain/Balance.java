package com.vassant.kata.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Balance {

    private int value;

    static Balance of(int value) {
        return new Balance(value);
    }

    Balance deposit(Amount amount) {
        return Balance.of(value + amount.getValue());
    }

    Balance withdraw(Amount amount) {
        return Balance.of(value - amount.getValue());
    }

    boolean hasEnoughSavings(Amount amount) {
        return value >= amount.getValue();
    }
}
