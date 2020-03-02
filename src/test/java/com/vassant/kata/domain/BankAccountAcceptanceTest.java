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
    @Parameters({"0,100,100",
            "-100,100,0"
    })
    public void a_deposit_in_a_account_should_be_add_in_balance(int initialBalance, int amountOfDeposit, int finaleBalance) {
        bankAccount = new BankAccount(Balance.of(initialBalance), operations, fakeClock);

        bankAccount.deposit(Amount.of(amountOfDeposit));

        assertThat(bankAccount.balance()).isEqualTo(Balance.of(finaleBalance));
    }

    @Test
    @Parameters({"100,100,0",
            "100,100,0"
    })
    public void withdraw_some_amount_from_account_should_be_remove_from_balance(int initialBalance, int amountOfWithDraw, int expectedBalance) {
        bankAccount = new BankAccount(Balance.of(initialBalance), operations, fakeClock);

        bankAccount.withdraw(Amount.of(amountOfWithDraw));

        assertThat(bankAccount.balance()).isEqualTo(Balance.of(expectedBalance));
    }


    @Test
    public void all_operations_on_account_should_appear_in_the_history() {
        int initialBalance = 500;
        bankAccount = new BankAccount(Balance.of(initialBalance), operations, fakeClock);
        final Amount aAmountOf100 = Amount.of(100);

        History historyExpected = History.from(asList(
                Operation.builder().operationType(OperationType.WITHDRAW).date(TODAY).amount(aAmountOf100).balance(Balance.of(400)).build(),
                Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY).amount(aAmountOf100).balance(Balance.of(500)).build(),
                Operation.builder().operationType(OperationType.WITHDRAW).date(TODAY).amount(aAmountOf100).balance(Balance.of(400)).build(),
                Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY).amount(aAmountOf100).balance(Balance.of(500)).build()));

        bankAccount.withdraw(aAmountOf100);
        bankAccount.deposit(aAmountOf100);
        bankAccount.withdraw(aAmountOf100);
        bankAccount.deposit(aAmountOf100);

        assertThat(bankAccount.history()).isEqualTo(historyExpected);
    }


}
