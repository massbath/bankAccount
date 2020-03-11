package com.vassant.kata.domain;

import com.vassant.kata.domain.ports.Clock;
import com.vassant.kata.domain.ports.Operations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BankAccountTest {
    @Mock
    private Operations operations;
    @Mock
    private Clock clock;
    private static final Amount AN_AMOUNT_OF_100 = Amount.of(100);
    private static final Amount AN_AMOUNT_OF_300 = Amount.of(300);
    private static final LocalDateTime NOW = LocalDateTime.now();
    private BankAccount bankAccount;

    @BeforeEach
    void setup() {
        bankAccount = new BankAccount(operations, clock);
        lenient().when(clock.getActualDate()).thenReturn(NOW);
    }

    @Nested
    class Deposit {
        @Test
        void should_be_save_in_operations() {
            Operation operationToSaveExpected = aOperation(OperationType.DEPOSIT, 100);

            bankAccount.deposit(AN_AMOUNT_OF_100);

            verify(operations).save(operationToSaveExpected);
        }

    }

    @Nested
    class Withdraw {

        @Test
        void should_be_save_in_operations() {
            when(operations.all()).thenReturn(Collections.singletonList(aOperation(OperationType.DEPOSIT, 100)));
            Operation operationToSaveExpected = aOperation(OperationType.WITHDRAW, 0);

            bankAccount.withdraw(AN_AMOUNT_OF_100);

            verify(operations).save(operationToSaveExpected);
        }

        @Test
        void more_than_my_saving_should_be_forbidden() {
            assertThatThrownBy(() -> bankAccount.withdraw(AN_AMOUNT_OF_300)).isInstanceOf(NotEnoughSavingsException.class);
        }

    }

    private Operation aOperation(OperationType operationType, int balance) {
        return Operation.builder()
                .operationType(operationType)
                .amount(AN_AMOUNT_OF_100)
                .balance(Balance.of(balance))
                .date(NOW)
                .build();
    }
}