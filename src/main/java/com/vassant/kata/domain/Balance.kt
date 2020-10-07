package com.vassant.kata.domain;

public final class Balance {

    private int value;

    private Balance(int value) {
        this.value = value;
    }

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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Balance)) return false;
        final Balance other = (Balance) o;
        if (this.value != other.value) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.value;
        return result;
    }

    public String toString() {
        return "Balance(value=" + this.value + ")";
    }
}
