package com.vassant.kata.domain;

import com.vassant.kata.domain.ports.Clock;
import com.vassant.kata.domain.ports.Operations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

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
    public void deposit_should_be_save_in_operations() {
        BankAccount account = new BankAccount( operations,clock);
        LocalDateTime now = LocalDateTime.now();

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
        BankAccount account = new BankAccount(operations,clock);
        LocalDateTime now = LocalDateTime.now();

        when(clock.getActualDate()).thenReturn(now);
        when(operations.all()).thenReturn(Collections.singletonList(Operation.builder()
                .operationType(OperationType.DEPOSIT)
                .amount(AN_AMOUNT_OF_100)
                .balance(Balance.of(100))
                .date(now)
                .build()));

        Operation operationToSaveExpected = Operation.builder()
                .operationType(OperationType.WITHDRAW)
                .amount(AN_AMOUNT_OF_100)
                .balance(Balance.of(0))
                .date(now)
                .build();


        account.withdraw(AN_AMOUNT_OF_100);

        verify(operations).save(operationToSaveExpected);
    }

    @Test
    public void withdraw_more_than_my_saving_should_be_forbidden() {
        BankAccount bankAccount =  new BankAccount(operations,clock);

        assertThatThrownBy(() -> bankAccount.withdraw(AN_AMOUNT_OF_300)).isInstanceOf(NotEnoughSavingsException.class);
    }
}