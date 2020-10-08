package com.vassant.kata.domain.adapters;

import com.vassant.kata.domain.Operation;
import com.vassant.kata.domain.ports.Operations;

import java.util.ArrayList;
import java.util.Collection;

public class OperationsInMemory implements Operations {

    private final Collection<Operation> operations = new ArrayList<>();

    @Override
    public Collection<Operation> all() {
        return operations;
    }

    @Override
    public void save(Operation operation) {
         operations.add(operation);
    }
}
