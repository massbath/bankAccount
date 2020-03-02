package com.vassant.kata.domain;

import lombok.RequiredArgsConstructor;

import static com.vassant.kata.domain.OperationType.DEPOSIT;
import static com.vassant.kata.domain.OperationType.WITHDRAW;

@RequiredArgsConstructor
final class BankAccount {

    private final Balance balance;
    private final Operations operations;
    private final Clock clock;

    void deposit(Amount amount) {
        balance.deposit(amount);
        saveOperation(amount, DEPOSIT);
    }

    void withdraw(Amount amount) {
        if (!balance.hasEnoughSavings(amount))
            throw new NotEnoughSavingsException();

        balance.withdraw(amount);
        saveOperation(amount, WITHDRAW);
    }

    private void saveOperation(Amount amount, OperationType deposit) {
        operations.save(Operation.builder()
                .operationType(deposit)
                .date(clock.getActualDate())
                .amount(amount)
                .balance(Balance.of(balance))
                .build());
    }

    Balance balance() {
        return Balance.of(balance);
    }

    History getHistory() {
        return History.from(operations.all());
    }

}
