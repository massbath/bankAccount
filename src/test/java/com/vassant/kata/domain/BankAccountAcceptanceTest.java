package com.vassant.kata.domain;

import com.vassant.kata.domain.adapters.FakeClock;
import com.vassant.kata.domain.adapters.OperationsInMemory;
import com.vassant.kata.domain.ports.BankAccountOperations;
import com.vassant.kata.domain.ports.Clock;
import com.vassant.kata.domain.ports.Operations;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BankAccountAcceptanceTest {

    private static final LocalDate TODAY = LocalDate.now();
    private final Clock fakeClock = new FakeClock(TODAY);
    private Operations operations = new OperationsInMemory();
    private BankAccountOperations bankAccount;




    @Test
    public void all_operations_on_account_should_appear_in_the_history() {
        int initialBalance = 0;
        bankAccount = new BankAccount(Balance.of(initialBalance), operations, fakeClock);
        final Amount aAmountOf100 = Amount.of(100);

        History historyExpected = History.from(asList(
                Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY).amount(aAmountOf100).balance(Balance.of(100)).build(),
                Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY).amount(aAmountOf100).balance(Balance.of(200)).build(),
                Operation.builder().operationType(OperationType.WITHDRAW).date(TODAY).amount(aAmountOf100).balance(Balance.of(100)).build(),
                Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY).amount(aAmountOf100).balance(Balance.of(200)).build()));

        bankAccount.deposit(aAmountOf100);
        bankAccount.deposit(aAmountOf100);
        bankAccount.withdraw(aAmountOf100);
        bankAccount.deposit(aAmountOf100);

        assertThat(bankAccount.history()).isEqualTo(historyExpected);
    }


}
