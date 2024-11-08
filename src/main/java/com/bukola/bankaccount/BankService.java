package com.bukola.bankaccount;

public class BankService {

    public void transferMoney(BankAccount from, BankAccount to, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        } else if (from.getBalance() >= amount) {
            from.setWithdrawBalance(amount);
            to.setDepositBalance(amount);
        } else {
            throw new ArithmeticException("Insufficient funds");
        }
    }
}
