package com.vassant.kata.domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class BankAccountAcceptanceTest {
    @Test
    @Parameters({"0,100,100",
            "-100,100,0"
    })
    public void a_deposit_in_a_account_should_be_add_in_balance(int initialBalance, int amountOfDeposit, int finaleBalance) {
        BankAccount bankAccount = new BankAccount(Balance.of(initialBalance));

        bankAccount.deposit(Amount.of(amountOfDeposit));

        Assertions.assertThat(bankAccount.getBalance()).isEqualTo(Balance.of(finaleBalance));
    }
}
