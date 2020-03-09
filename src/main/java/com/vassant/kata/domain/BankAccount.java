package com.vassant.kata.domain;

import com.vassant.kata.domain.ports.BankAccountOperations;
import com.vassant.kata.domain.ports.Clock;
import com.vassant.kata.domain.ports.Operations;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

import static com.vassant.kata.domain.OperationType.DEPOSIT;
import static com.vassant.kata.domain.OperationType.WITHDRAW;

@RequiredArgsConstructor
final class BankAccount implements BankAccountOperations {

    private final Operations operations;
    private final Clock clock;

    @Override
    public void deposit(Amount amount) {
        saveOperation(amount, DEPOSIT,balance().deposit(amount));
    }

    @Override
    public void withdraw(Amount amount) {
        Balance balance = balance();
        if (!balance.hasEnoughSavings(amount))
            throw new NotEnoughSavingsException();

        saveOperation(amount, WITHDRAW,balance.withdraw(amount));
    }

    @Override
    public Balance balance() {
        return operations.all().stream()
                .max(Comparator.comparing(Operation::getDate))
                .map(Operation::getBalance)
                .orElse(Balance.of(0));
    }

    @Override
    public History history() {
        return History.from(operations.all());
    }

    private void saveOperation(Amount amount, OperationType deposit, Balance balance) {
        operations.save(Operation.builder()
                .operationType(deposit)
                .date(clock.getActualDate())
                .amount(amount)
                .balance(balance)
                .build());
    }

}
