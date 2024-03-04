package com.sofe3980u.BankingSystem;

public interface Account {
	public String getAccountType();
	public String getAccountName();
	public String getAccountHolder();
	public double getAccountBalance();
	public void deposit(double amount);
}
