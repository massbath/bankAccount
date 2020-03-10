package com.vassant.kata.domain;

import lombok.Value;

import java.util.Collection;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toUnmodifiableList;


@Value
public final class History {
    Collection<Operation> operations;

    private History(Collection<Operation> operations) {
        this.operations = operations;
    }

    static History from(Collection<Operation> allOperations) {
        Collection<Operation> operationByDate = allOperations.stream()
                .sorted(comparing(Operation::getDate))
                .collect(toUnmodifiableList());

        return new History(operationByDate);
    }
}
