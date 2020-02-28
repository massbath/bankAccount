package com.vassant.kata.domain.adapters;

import com.vassant.kata.domain.Operation;
import com.vassant.kata.domain.Operations;

import java.util.ArrayList;
import java.util.List;

public class OperationsInMemory implements Operations {

    private final List<Operation> operations = new ArrayList<>();

    @Override
    public List<Operation> all() {
        return operations;
    }

    @Override
    public void save(Operation operation) {
         operations.add(operation);
    }
}
