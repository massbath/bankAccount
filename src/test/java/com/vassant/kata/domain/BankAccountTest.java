package com.vassant.kata.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BankAccountTest {

    private static final Amount AN_AMOUNT_OF_100 = Amount.of(100);
    private static final Amount AN_AMOUNT_OF_200 = Amount.of(200);
    private static final Amount AN_AMOUNT_OF_300 = Amount.of(300);


    @Test
    public void a_deposit_on_a_account_should_change_the_balance_to_be_equal_to_the_amount_of_deposit() {
        BankAccount account = new BankAccount( Balance.of(0));

        account.deposit(AN_AMOUNT_OF_100);

        assertThat(account.getBalance()).isEqualTo(Balance.of(100));
    }

    @Test
    public void many_deposit_on_a_account_should_change_the_balance_to_be_equal_to_the_sum_of_amount_of_deposit() {
        BankAccount account = new BankAccount( Balance.of(0));

        account.deposit(AN_AMOUNT_OF_100);
        account.deposit(AN_AMOUNT_OF_200);
        account.deposit(AN_AMOUNT_OF_300);

        assertThat(account.getBalance()).isEqualTo(Balance.of(100 + 200 + 300));
    }


    @Test
    public void withdraw_some_amount_from_account_should_be_remove_from_balance() {
        BankAccount account = new BankAccount(Balance.of(200));

        account.withdraw(AN_AMOUNT_OF_100);

        assertThat(account.getBalance()).isEqualTo(Balance.of(100));
    }

    @Test
    public void withdraw_more_than_my_saving_should_be_forbidden() {
        BankAccount bankAccount = new BankAccount(Balance.of(100));

        assertThatThrownBy(() -> bankAccount.withdraw(AN_AMOUNT_OF_300)).isInstanceOf(NotEnoughSavingsException.class);
    }
}