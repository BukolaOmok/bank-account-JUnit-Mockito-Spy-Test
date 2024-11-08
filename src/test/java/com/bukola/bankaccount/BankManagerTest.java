package com.bukola.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankManagerTest {

    @InjectMocks
    BankManager manager;

    @Mock
    BankAccount account1;

    @Mock
    BankAccount account2;


    @BeforeEach
    void setUp() {
        manager = new BankManager();
        manager.addBankAccount("01", account1);
        manager.addBankAccount("02", account2);
    }

    @Test
    public void testAddBankAccountAndCheckBalance() {
        when(account1.getBalance()).thenReturn(200.0);
        assertEquals(200.0, manager.checkBalance("01"));
    }

    @Test
    public void testTransfer() {
        doNothing().when(account1).setWithdrawBalance(300.0);
        doNothing().when(account2).setDepositBalance(300.0);

        manager.transfer("01", "02", 300.0);

        verify(account1).setWithdrawBalance(300.0);
        verify(account2).setDepositBalance(300.0);

    }

    @Test
    public void calculateInterests() {
        when(account1.calculateInterest(3.5, 5)).thenReturn(35.0);
        when(account2.calculateInterest(3.5, 5)).thenReturn(70.0);

        manager.calculateInterestForAll(3.5, 5);

        verify(account1).setDepositBalance(35.0);
        verify(account2).setDepositBalance(70.0);

    }
}
