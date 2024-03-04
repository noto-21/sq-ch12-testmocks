package com.sofe3980u.BankingSystem;

public interface Account {
	public String getAccountNumber();
	public String getAccountType();
	public String getAccountName();
	public String getAccountHolder();
	public String getEmailAddress();
	public String getResidentialAddress();
	public double getAccountBalance();
	public void deposit(double amount);
}
