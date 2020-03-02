package com.vassant.kata.domain;

import com.vassant.kata.domain.ports.BankAccountOperations;
import com.vassant.kata.domain.ports.Clock;
import com.vassant.kata.domain.ports.Operations;
import lombok.RequiredArgsConstructor;

import static com.vassant.kata.domain.OperationType.DEPOSIT;
import static com.vassant.kata.domain.OperationType.WITHDRAW;

@RequiredArgsConstructor
final class BankAccount implements BankAccountOperations {

    private final Balance balance;
    private final Operations operations;
    private final Clock clock;

    @Override
    public void deposit(Amount amount) {
        balance.deposit(amount);
        saveOperation(amount, DEPOSIT);
    }

    @Override
    public void withdraw(Amount amount) {
        if (!balance.hasEnoughSavings(amount))
            throw new NotEnoughSavingsException();

        balance.withdraw(amount);
        saveOperation(amount, WITHDRAW);
    }

    @Override
    public Balance balance() {
        return Balance.of(balance);
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
                .balance(Balance.of(balance))
                .build());
    }

}
