package com.vassant.kata.domain;

import com.vassant.kata.domain.adapters.FakeClock;
import com.vassant.kata.domain.adapters.OperationsInMemory;
import com.vassant.kata.domain.ports.BankAccountOperations;
import com.vassant.kata.domain.ports.Clock;
import com.vassant.kata.domain.ports.Operations;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class BankAccountAcceptanceTest {

    private static final LocalDateTime TODAY = LocalDateTime.now();
    private final Clock fakeClock = new FakeClock(TODAY);
    private Operations operations = new OperationsInMemory();
    private Amount aAmountOf100 = Amount.of(100);

    @Test
    void all_operations_on_account_should_appear_in_the_history() {
        BankAccountOperations bankAccount = new BankAccount(operations, fakeClock);

        History historyExpected = History.from(asList(
                Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY).amount(aAmountOf100).balance(Balance.of(100)).build(),
                Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY.plusDays(1)).amount(aAmountOf100).balance(Balance.of(200)).build(),
                Operation.builder().operationType(OperationType.WITHDRAW).date(TODAY.plusDays(2)).amount(aAmountOf100).balance(Balance.of(100)).build(),
                Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY.plusDays(3)).amount(aAmountOf100).balance(Balance.of(200)).build()));

        bankAccount.deposit(aAmountOf100);
        bankAccount.deposit(aAmountOf100);
        bankAccount.withdraw(aAmountOf100);
        bankAccount.deposit(aAmountOf100);

        assertThat(bankAccount.history()).isEqualTo(historyExpected);
    }


}
