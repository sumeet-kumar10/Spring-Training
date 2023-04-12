package com.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("transactionService")
@Transactional
public class TransactionService {
	
	@Autowired
	private BankingService bankingService;

	public BankingService getbankingService() {
		return bankingService;
	}

	public void setbankingService(BankingService bankingService) {
		this.bankingService = bankingService;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void moneyTransfer(int creditAmount, int debitAmount, int amount) throws InsufficientBalanceException {
		bankingService.doCredit(creditAmount, amount);
		bankingService.doDebit(debitAmount, amount);
	}
}
