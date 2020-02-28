package com.vassant.kata.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final class BankAccount {

    private final Balance balance;

     void deposit(Amount amount) {
         balance.add(amount);
    }

     Balance getBalance() {
       return balance;
    }
}
