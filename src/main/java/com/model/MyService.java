package com.model;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class MyService {

	@Autowired
	private AccountsDAO dao;

	public void doServe(int acid, int amount) {
		AccountsDTO dto = dao.findByID(acid);
		dto.setAmount(amount);

		dao.updateAccounts(dto);
	}

	public AccountsDAO getDao() {
		return dao;
	}

	public void setDao(AccountsDAO dao) {
		this.dao = dao;
	}

}
