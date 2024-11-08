package com.bukola.bankaccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankAccountTest {

    @Spy
    BankAccount bankAccount;

    @Test
    public void deposit() {
        BankAccount bankAccount = createBankAccount(200.0, 200.0, true, false);
        assertEquals(400.0, bankAccount.getBalance());
    }

    @Test
    public void depositNegativeBalance() {
        BankAccount bankAccount = createBankAccount(-200.0, 200.0, true, false);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.getBalance());
    }

    @Test
    public void withdraw() {
        BankAccount bankAccount = createBankAccount(100.0, 200.0, false, true);
        assertEquals(100.0, bankAccount.getBalance());
    }

    @Test
    void withdrawMoreThanBalance() {
        BankAccount bankAccount = createBankAccount(300.0, 200.0, false, true);
        assertThrows(ArithmeticException.class, () -> bankAccount.getBalance());
    }

    @Test
    void withdrawZeroBalance() {
        BankAccount bankAccount = createBankAccount(0.0, 200.0, false, true);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.getBalance());
    }

    @Test
    void mockCalculateInterestRate () {
        when(bankAccount.calculateInterest(4.5, 5)).thenReturn(45.0);

        bankAccount.setDepositBalance(200);
        bankAccount.setWithdrawBalance(200);

        assertEquals(45, bankAccount.calculateInterest(4.5, 5));

        verify(bankAccount).calculateInterest(4.5, 5);
        verify(bankAccount).setDepositBalance(200);
        verify(bankAccount).setWithdrawBalance(200);
    }

    @Test
    void mockCalculateInterestRateFrequencyOfCall () {
        when(bankAccount.calculateInterest(4.5, 5)).thenReturn(45.0);

        bankAccount.calculateInterest(4.5, 5);
        bankAccount.calculateInterest(4.5, 5);
        bankAccount.calculateInterest(4.5, 5);

        verify(bankAccount, times(3)).calculateInterest(4.5, 5);
    }

    private BankAccount createBankAccount(double amount, double balance, boolean deposit, boolean withdraw) {
        BankAccount bankAccounts = new BankAccount();
        bankAccounts.setAmount(amount);
        bankAccounts.setBalance(balance);
        bankAccounts.setDeposit(deposit);
        bankAccounts.setWithdraw(withdraw);
        return bankAccounts;
    }
}
