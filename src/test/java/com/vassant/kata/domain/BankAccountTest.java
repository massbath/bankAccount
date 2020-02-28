package com.vassant.kata.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BankAccountTest {

    @Test
    public void given_a_deposit_on_a_account_balance_should_return_the_amount_of_deposit() {
        BankAccount account = new BankAccount(Balance.of(0));
        account.deposit(Amount.of(100));
        Assertions.assertThat(account.getBalance()).isEqualTo(Balance.of(100));
    }
}