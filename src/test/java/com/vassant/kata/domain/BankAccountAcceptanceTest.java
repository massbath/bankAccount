package com.vassant.kata.domain;

import com.vassant.kata.domain.adapters.FakeClock;
import com.vassant.kata.domain.adapters.OperationsInMemory;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BankAccountAcceptanceTest {

    private final LocalDate TODAY = LocalDate.now();
    private final Clock fakeClock  = new FakeClock(TODAY);
    private Operations operations = new OperationsInMemory();

    @Test
    @Parameters({"0,100,100",
            "-100,100,0"
    })
    public void a_deposit_in_a_account_should_be_add_in_balance(int initialBalance, int amountOfDeposit, int finaleBalance) {
        BankAccount bankAccount = new BankAccount(Balance.of(initialBalance), operations,fakeClock);

        bankAccount.deposit(Amount.of(amountOfDeposit));

        Assertions.assertThat(bankAccount.getBalance()).isEqualTo(Balance.of(finaleBalance));
    }

    @Test
    @Parameters({"100,100,0",
            "100,100,0"
    })
    public void withdraw_some_amount_from_account_should_be_remove_from_balance(int initialBalance, int amountOfWithDraw, int expectedBalance) {
        BankAccount bankAccount = new BankAccount(Balance.of(initialBalance),operations,fakeClock);

        bankAccount.withdraw(Amount.of(amountOfWithDraw));

        assertThat(bankAccount.getBalance()).isEqualTo(Balance.of(expectedBalance));
    }

    @Test
    public void deposits_on_account_should_appear_in_the_historic() {

        BankAccount account = new BankAccount(Balance.of(0),operations,fakeClock);
        History historyExpected =  History.builder()
                .operation(Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY).amount(Amount.of(100)).balance(Balance.of(100)).build())
                .operation(Operation.builder().operationType(OperationType.DEPOSIT).date(TODAY).amount(Amount.of(200)).balance(Balance.of(300)).build())
                .build();

        account.deposit(Amount.of(100));
        account.deposit(Amount.of(200));

        assertThat(account.getHistory()).isEqualTo(historyExpected);
    }

    @Test
    public void withdraws_on_account_should_appear_in_the_historic() {

        BankAccount account = new BankAccount(Balance.of(500),operations,fakeClock);
        History historyExpected =  History.builder()
                .operation(Operation.builder().operationType(OperationType.WITHDRAW).date(TODAY).amount(Amount.of(100)).balance(Balance.of(400)).build())
                .operation(Operation.builder().operationType(OperationType.WITHDRAW).date(TODAY).amount(Amount.of(200)).balance(Balance.of(200)).build())
                .build();

        account.withdraw(Amount.of(100));
        account.withdraw(Amount.of(200));

        assertThat(account.getHistory()).isEqualTo(historyExpected);
    }
}
