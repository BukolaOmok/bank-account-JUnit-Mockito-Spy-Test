package com.bukola.bankaccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InvalidClassException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

    @Mock
    BankAccount from;

    @Mock
    BankAccount to;

    @InjectMocks
    BankService bankService;

    @Test
    void transferCallsWithdrawOnFromAndDepositOnTo() {

        double amount = 300;
        when(from.getBalance()).thenReturn(600.0);

        bankService.transferMoney(from, to, amount);

        verify(from).setWithdrawBalance(amount);
        verify(to).setDepositBalance(amount);
    }

    @Test
    void transferAmountGreaterThanBalance() {
        double amount = 300;
        when(from.getBalance()).thenReturn(200.0);

        assertThrows(ArithmeticException.class, () -> bankService.transferMoney(from, to, amount));
    }

    @Test
    void transferNegativeAmount() {
        double amount = -300;
        assertThrows(IllegalArgumentException.class, () -> bankService.transferMoney(from, to, amount));
    }
}
