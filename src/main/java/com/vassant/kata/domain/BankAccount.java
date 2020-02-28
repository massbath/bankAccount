package com.vassant.kata.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
final class BankAccount {

    private Balance balance;

     void deposit(Amount amount) {
         this.balance.add(amount);
    }

     Balance getBalance() {
       return balance;
    }
}
