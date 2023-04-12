package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bank")
public class BankController {
	@Autowired
	CreateBankService bank;

	@GetMapping(value = "menu")
	public ModelAndView menu(ModelAndView modelAndView) {
		modelAndView.setViewName("menu");
		return modelAndView;
	}
	
	@GetMapping(value = "createAccount")
	public ModelAndView create(ModelAndView modelAndView) {
		BankDTO bank = new BankDTO();
		modelAndView.addObject("bank", bank);
		modelAndView.setViewName("createAccount");
		return modelAndView;

	}

	@RequestMapping(value = "createAccount", method = RequestMethod.POST)
	public ModelAndView addAccount(BankDTO bankDTO, ModelAndView modelAndView) throws UserExistException {
		bank.createUser(bankDTO);
		modelAndView.setViewName("User");
		return modelAndView;

	}

	@GetMapping(value = "checkBalance")
	public ModelAndView checkBalance(ModelAndView modelAndView) {
		BankDTO bank = new BankDTO();
		modelAndView.addObject("bank", bank);
		modelAndView.setViewName("checkBalance");
		return modelAndView;

	}

	@RequestMapping(value = "checkBalance", method = RequestMethod.POST)
	public ModelAndView find(BankDTO banks, ModelAndView modelAndView) {
		int num = banks.getUid();
		BankDTO banlance = bank.checkBalance(num);
		modelAndView.addObject("Balance", banlance);
		modelAndView.setViewName("Balance");
		return modelAndView;
	}

	@GetMapping(value = "transfer")
	public ModelAndView transaction(ModelAndView modelAndView) {
		Transaction transaction = new Transaction();
		modelAndView.addObject("transfer", transaction);
		modelAndView.setViewName("transfer");
		return modelAndView;

	}

	@RequestMapping(value = "transfer", method = RequestMethod.POST)
	public ModelAndView transactionsuccess(ModelAndView modelAndView, Transaction transaction) {
		try {
			bank.transfer(transaction);
		} catch (Exception exception) {
			modelAndView.addObject("error", exception);
			modelAndView.setViewName("InsufficientBalance");
			return modelAndView;
		}
		modelAndView.setViewName("transferSuccessful");
		return modelAndView;

	}

}
