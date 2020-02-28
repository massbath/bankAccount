package com.vassant.kata.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final class BankAccount {

    private final Balance balance;

     void deposit(Amount amount) {
         balance.add(amount);
    }

    void withdraw(Amount amount) {
        if(!balance.hasEnoughSavings(amount)){
            throw new NotEnoughSavingsException();
        }
        balance.remove(amount);
    }

     Balance getBalance() {
       return balance;
    }
}
