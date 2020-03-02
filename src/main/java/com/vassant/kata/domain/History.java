package com.vassant.kata.domain;

import lombok.Value;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.unmodifiableList;


@Value
public final class History {
    List<Operation> operations;

    static History from(List<Operation> allOperations) {
        allOperations.sort(Comparator.comparing(Operation::getDate));
        return new History(unmodifiableList(allOperations));
    }
}
