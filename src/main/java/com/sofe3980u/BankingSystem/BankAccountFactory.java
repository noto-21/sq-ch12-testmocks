package com.sofe3980u.BankingSystem;

public class BankAccountFactory {
	public Account createAccount(String type, String accountName, String accountHolder, double initialBalance) {
        switch (type.toLowerCase()) {
            case "savings":
                return new SavingsAccount(accountName, accountHolder, initialBalance);
            case "checking":
                return new CheckingAccount(accountName, accountHolder, initialBalance);
            default:
                throw new IllegalArgumentException("Invalid account type: " + type);
        }
    }
}
