package com.example.demo;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("service")
@Transactional
public class CreateBankService implements BankServiceInterface{

	@Autowired
	BankDAO bankDAO;

	@Override
	public void createUser(BankDTO bank) {
		bankDAO.save(bank);
	}

	@Override
	public BankDTO checkBalance(int id) {
		Optional<BankDTO> bankObject = bankDAO.findById(Integer.valueOf(id));
		BankDTO bankAccount = bankObject.get();
		return bankAccount;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void checkUser(int id) throws UserExistException{
		Optional<BankDTO> userObject = bankDAO.findById(Integer.valueOf(id));
		boolean result = userObject.isPresent();
		if(result == true) {
			throw new UserExistException("User already exist");
		}
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void transfer(Transaction transfer) throws InsufficientBalanceException {
		int creditId = transfer.getCredit();
		int debitId = transfer.getDebit();
		int amount = transfer.getAmount();
		debitransfer(debitId, amount);
		creditransfer(creditId, amount);

	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void debitransfer(int id, int amount) throws InsufficientBalanceException {
		Optional<BankDTO> bankObject = bankDAO.findById(Integer.valueOf(id));
		BankDTO bankAccount1 = bankObject.get();
		System.out.println(bankAccount1.getAmount());
		if (bankAccount1.getAmount() < amount) {
			throw new InsufficientBalanceException("Insufficient balance");
		}
		bankAccount1.setAmount(bankAccount1.getAmount() - amount);
		System.out.println(bankAccount1.getAmount());
		bankDAO.save(bankAccount1);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void creditransfer(int id, int amount) {
		Optional<BankDTO> bankObject = bankDAO.findById(Integer.valueOf(id));
		BankDTO bankAccount = bankObject.get();
		bankAccount.setAmount(bankAccount.getAmount() + amount);
		System.out.println(bankAccount.getAmount());
		bankDAO.save(bankAccount);
	}
}