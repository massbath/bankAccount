package com.vassant.kata.domain;

import com.vassant.kata.domain.ports.BankAccountOperations;
import com.vassant.kata.domain.ports.Clock;
import com.vassant.kata.domain.ports.Operations;

import java.util.Comparator;

import static com.vassant.kata.domain.OperationType.DEPOSIT;
import static com.vassant.kata.domain.OperationType.WITHDRAW;

final class BankAccount implements BankAccountOperations {

    private  Balance balance;
    private final Operations operations;
    private final Clock clock;

    public BankAccount(Balance balance, Operations operations, Clock clock) {
        this.balance = balance;
        this.operations = operations;
        this.clock = clock;
    }

    @Override
    public void deposit(Amount amount) {
        balance = balance.deposit(amount);
        saveOperation(amount, DEPOSIT);
    }

    @Override
    public void withdraw(Amount amount) {
        if (!balance.hasEnoughSavings(amount))
            throw new NotEnoughSavingsException();

        balance = balance.withdraw(amount);
        saveOperation(amount, WITHDRAW);
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

    private void saveOperation(Amount amount, OperationType deposit) {
        operations.save(Operation.builder()
                .operationType(deposit)
                .date(clock.getActualDate())
                .amount(amount)
                .balance(balance)
                .build());
    }

}
