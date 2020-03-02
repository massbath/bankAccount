package com.vassant.kata.domain.ports;

import com.vassant.kata.domain.Operation;

import java.util.List;

public interface Operations {
    List<Operation> all();
    void save(Operation operation);
}
