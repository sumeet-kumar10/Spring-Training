package com.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bankingService")
@Transactional
public class BankingService {

	@Autowired
	private AccountsDAO dao;

	@Transactional
	public void doCredit(int acid, int creditAmount) {
		AccountsDTO dto = dao.findByID(acid);
		int amount = dto.getAmount();
		int newAmount = amount + creditAmount;
		dto.setAmount(newAmount);
		dao.updateAccounts(dto);
	}

	@Transactional
	public void doDebit(int acid, int debitAmount) throws InsufficientBalanceException {
		AccountsDTO dto = dao.findByID(acid);
		int amount = dto.getAmount();
		if (amount < debitAmount) {
			throw new InsufficientBalanceException("Not enough money to transfer....");
		}
		int newAmount = amount - debitAmount;
		dto.setAmount(newAmount);
		dao.updateAccounts(dto);
	}
}







