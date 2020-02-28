package com.vassant.kata.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BankAccountAcceptanceTest {
    @Test
    public void a_deposit_in_a_account_should_be_add_in_balance() {
        BankAccount bankAccount = new BankAccount(Balance.of(0));

        bankAccount.deposit(Amount.of(100));

        Assertions.assertThat(bankAccount.getBalance()).isEqualTo(Balance.of(100));
    }
}
