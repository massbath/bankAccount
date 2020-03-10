package com.vassant.kata.domain;

import com.vassant.kata.domain.ports.Clock;
import com.vassant.kata.domain.ports.Operations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
    }

    @Nested
    class deposit {
        @Test
        void should_be_save_in_operations() {

            when(clock.getActualDate()).thenReturn(NOW);

            Operation operationToSaveExpected = Operation.builder()
                    .operationType(OperationType.DEPOSIT)
                    .amount(AN_AMOUNT_OF_100)
                    .balance(Balance.of(100))
                    .date(NOW)
                    .build();

            bankAccount.deposit(AN_AMOUNT_OF_100);

            verify(operations).save(operationToSaveExpected);
        }

    }
    @Nested
    class withdraw {
        @Test
        void should_be_save_in_operations() {
            when(clock.getActualDate()).thenReturn(NOW);
            when(operations.all()).thenReturn(Collections.singletonList(Operation.builder()
                    .operationType(OperationType.DEPOSIT)
                    .amount(AN_AMOUNT_OF_100)
                    .balance(Balance.of(100))
                    .date(NOW)
                    .build()));

            Operation operationToSaveExpected = Operation.builder()
                    .operationType(OperationType.WITHDRAW)
                    .amount(AN_AMOUNT_OF_100)
                    .balance(Balance.of(0))
                    .date(NOW)
                    .build();


            bankAccount.withdraw(AN_AMOUNT_OF_100);

            verify(operations).save(operationToSaveExpected);
        }
        @Test
        void more_than_my_saving_should_be_forbidden() {
            assertThatThrownBy(() -> bankAccount.withdraw(AN_AMOUNT_OF_300)).isInstanceOf(NotEnoughSavingsException.class);
        }

    }
}