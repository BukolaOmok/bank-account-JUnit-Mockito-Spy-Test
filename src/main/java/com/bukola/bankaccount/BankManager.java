package com.bukola.bankaccount;

import java.util.HashMap;
import java.util.Map;

public class BankManager {
    private Map<String, BankAccount> bankAccounts;

    public BankManager() {
        this.bankAccounts = new HashMap<>();
    }

    public void addBankAccount(String accountId, BankAccount bankAccount) {
        bankAccounts.put(accountId, bankAccount);
    }

    public void transfer(String fromAccountId, String toAccountId, double amount) {
        BankAccount fromAccount = bankAccounts.get(fromAccountId);
        BankAccount toAccount = bankAccounts.get(toAccountId);

        if (fromAccount != null && toAccount != null) {
            fromAccount.setWithdrawBalance(amount);
            toAccount.setDepositBalance(amount);
        } else {
            throw new IllegalArgumentException("One or both accounts are not found");
        }
    }

    public double checkBalance(String accountId) {
        BankAccount account = bankAccounts.get(accountId);
        if (account != null) {
            return account.getBalance();
        } else {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
    }

    public void calculateInterestForAll(double interestRate, int years) {
        for (Map.Entry<String, BankAccount> entry : bankAccounts.entrySet()) {
            BankAccount account = entry.getValue();
            double interest = account.calculateInterest(interestRate, years);
            account.setDepositBalance(interest);
        }
    }

    public Map<String, BankAccount> getAccounts() {
        return new HashMap<>(bankAccounts);
    }

}
