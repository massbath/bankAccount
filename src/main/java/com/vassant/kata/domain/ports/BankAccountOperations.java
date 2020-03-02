package com.vassant.kata.domain.ports;

import com.vassant.kata.domain.Amount;
import com.vassant.kata.domain.Balance;
import com.vassant.kata.domain.History;

public interface BankAccountOperations {
    void deposit(Amount amount);
    void withdraw(Amount amount);
    Balance balance();
    History history();
}
