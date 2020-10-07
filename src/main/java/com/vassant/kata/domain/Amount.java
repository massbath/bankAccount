package com.vassant.kata.domain;

public final class Amount {
    private final int value;

    private Amount(int value) {
        this.value = value;
    }

    static Amount of(int value) {
        if (value < 0)
            throw new NegativeAmountNotAllowedException();
        return new Amount(value);
    }

    public int getValue() {
        return this.value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Amount)) return false;
        final Amount other = (Amount) o;
        if (this.getValue() != other.getValue()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getValue();
        return result;
    }

    public String toString() {
        return "Amount(value=" + this.getValue() + ")";
    }
}
