package com.vassant.kata.domain;

import java.util.List;

public interface Operations {
    List<Operation> all();
    void save(Operation operation);
}
