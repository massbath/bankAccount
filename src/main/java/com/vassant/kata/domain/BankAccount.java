package com.vassant.kata.domain;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.unmodifiableList;

@RequiredArgsConstructor
final class BankAccount {

    private final Balance balance;
    private final Operations operations;
    private final Clock clock;

     void deposit(Amount amount) {
         balance.deposit(amount);
         operations.save(Operation.builder().operationType(OperationType.DEPOSIT).date(clock.getActualDate()).amount(amount).balance(Balance.of(balance)).build());
    }

    void withdraw(Amount amount) {
        if(!balance.hasEnoughSavings(amount)){
            throw new NotEnoughSavingsException();
        }
        balance.withdraw(amount);
    }

     Balance getBalance() {
       return balance;
    }

    History getHistory() {
         List<Operation> allOperations = operations.all();
         allOperations.sort(Comparator.comparing(Operation::getDate));
         return History.builder().operations(unmodifiableList(allOperations)).build();
    }
}
