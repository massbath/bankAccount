package com.vassant.kata.domain;

import java.util.Collection;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toUnmodifiableList;


public final class History {
    private final Collection<Operation> operations;

    private History(Collection<Operation> operations) {
        this.operations = operations;
    }

    static History from(Collection<Operation> allOperations) {
        Collection<Operation> operationByDate = allOperations.stream()
                .sorted(comparing(Operation::getDate))
                .collect(toUnmodifiableList());

        return new History(operationByDate);
    }

    public Collection<Operation> getOperations() {
        return this.operations;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof History)) return false;
        final History other = (History) o;
        final Object this$operations = this.getOperations();
        final Object other$operations = other.getOperations();
        if (this$operations == null ? other$operations != null : !this$operations.equals(other$operations))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $operations = this.getOperations();
        result = result * PRIME + ($operations == null ? 43 : $operations.hashCode());
        return result;
    }

    public String toString() {
        return "History(operations=" + this.getOperations() + ")";
    }
}
