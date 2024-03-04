package com.sofe3980u.BankingSystem;

public class CheckingAccount implements Account {
	private String accountType;
    private String accountName;
    private String accountHolder;
    private double accountBalance;

    public CheckingAccount(String accountName, String accountHolder, double initialBalance) {
        this.accountType = "Checking";
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        this.accountBalance = initialBalance;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    @Override
    public String getAccountName() {
        return accountName;
    }

    @Override
    public String getAccountHolder() {
        return accountHolder;
    }

    @Override
    public double getAccountBalance() {
        return accountBalance;
    }

    @Override
    public void deposit(double amount) {
        accountBalance += amount;
    }
}
