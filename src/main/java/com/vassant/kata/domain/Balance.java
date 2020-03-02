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

    static Balance of(Balance balance) {
        return of(balance.value);
    }

    void deposit(Amount amount) {
        value += amount.getValue();
    }

    void withdraw(Amount amount) {
        value -= amount.getValue();
    }

    boolean hasEnoughSavings(Amount amount) {
        return value >= amount.getValue();
    }
}
