package com.bukola.bankaccount;

public class BankAccount {
    private double balance;
    private double amount;
    private boolean deposit;
    private boolean withdraw;


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean setDeposit(boolean deposit) {
        this.deposit = deposit;
        return deposit;
    }

    public boolean setWithdraw(boolean withdraw) {
        this.withdraw = withdraw;
        return withdraw;
    }

    public void setDepositBalance(double amount) {
        if (amount > 0.0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
    }

    public void setWithdrawBalance(double amount) {
        if (amount > 0 && amount < balance) {
            balance -= amount;
        } else if (amount <= 0.0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        } else if (amount > balance) {
            throw new ArithmeticException("Insufficient funds");
        }
    }

    public double getBalance() {
        if (deposit) {
            setDepositBalance(amount);
        }
        if (withdraw) {
            setWithdrawBalance(amount);
        }
        return balance;
    }

    public double calculateInterest(double interestRate, int years) {
        return balance * interestRate * years / 100;
    }
}
