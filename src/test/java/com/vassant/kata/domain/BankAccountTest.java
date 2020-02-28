package com.vassant.kata.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountTest {

    private static final Amount AN_AMOUNT_OF_100 = Amount.of(100);
    private static final Amount AN_AMOUNT_OF_200 = Amount.of(200);
    private static final Amount AN_AMOUNT_OF_300 = Amount.of(300);
    @Mock
    private Operations operations;
    @Mock
    private Clock clock;

    @Test
    public void a_deposit_on_a_account_should_change_the_balance_to_be_equal_to_the_amount_of_deposit() {
        BankAccount account = new BankAccount( Balance.of(0),operations,clock);

        account.deposit(AN_AMOUNT_OF_100);

        assertThat(account.balance()).isEqualTo(Balance.of(100));
    }

    @Test
    public void many_deposit_on_a_account_should_change_the_balance_to_be_equal_to_the_sum_of_amount_of_deposit() {
        BankAccount account = new BankAccount( Balance.of(0),operations,clock);

        account.deposit(AN_AMOUNT_OF_100);
        account.deposit(AN_AMOUNT_OF_200);
        account.deposit(AN_AMOUNT_OF_300);

        assertThat(account.balance()).isEqualTo(Balance.of(100 + 200 + 300));
    }


    @Test
    public void withdraw_some_amount_from_account_should_be_remove_from_balance() {
        BankAccount account = new BankAccount( Balance.of(200),operations,clock);

        account.withdraw(AN_AMOUNT_OF_100);

        assertThat(account.balance()).isEqualTo(Balance.of(100));
    }

    @Test
    public void many_withdraw_from_account_should_be_remove_from_balance() {
        BankAccount account =  new BankAccount( Balance.of(200),operations,clock);

        account.withdraw(AN_AMOUNT_OF_100);
        account.withdraw(AN_AMOUNT_OF_100);

        assertThat(account.balance()).isEqualTo(Balance.of(0));
    }


    @Test
    public void withdraw_more_than_my_saving_should_be_forbidden() {
        BankAccount bankAccount =  new BankAccount( Balance.of(100),operations,clock);

        assertThatThrownBy(() -> bankAccount.withdraw(AN_AMOUNT_OF_300)).isInstanceOf(NotEnoughSavingsException.class);
    }

    @Test
    public void deposit_should_be_save_in_operations() {
        BankAccount account = new BankAccount( Balance.of(0),operations,clock);
        LocalDate now = LocalDate.now();

        when(clock.getActualDate()).thenReturn(now);

        Operation operationToSaveExpected = Operation.builder()
                .operationType(OperationType.DEPOSIT)
                .amount(AN_AMOUNT_OF_100)
                .balance(Balance.of(100))
                .date(now)
                .build();

        account.deposit(AN_AMOUNT_OF_100);

        verify(operations).save(operationToSaveExpected);
    }

    @Test
    public void withdraw_should_be_save_in_operations() {
        BankAccount account = new BankAccount( Balance.of(100),operations,clock);
        LocalDate now = LocalDate.now();

        when(clock.getActualDate()).thenReturn(now);

        Operation operationToSaveExpected = Operation.builder()
                .operationType(OperationType.WITHDRAW)
                .amount(AN_AMOUNT_OF_100)
                .balance(Balance.of(0))
                .date(now)
                .build();

        account.withdraw(AN_AMOUNT_OF_100);

        verify(operations).save(operationToSaveExpected);
    }
}