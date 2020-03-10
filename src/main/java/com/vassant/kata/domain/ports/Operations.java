package com.vassant.kata.domain.ports;

import com.vassant.kata.domain.Operation;

import java.util.Collection;

public interface Operations {
    Collection<Operation> all();
    void save(Operation operation);
}
