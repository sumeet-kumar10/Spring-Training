package com.example.demo;

public interface BankServiceInterface {
	public void createUser(BankDTO bank) throws UserExistException;

	public BankDTO checkBalance(int id);

	public void transfer(Transaction transaction) throws InsufficientBalanceException;
}