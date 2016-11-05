package org.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.jpa.model.Account;
import org.test.jpa.repo.AccountRepository;
import org.test.model.form.AccountForm;

@Service
public class AccountService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account saveAccount(AccountForm form){
		return accountRepository.save(form.newAccount());
	}
	
	public Account findAccount(String email){
		return accountRepository.findByEmail(email);
	}
	
	public Account findAccount(Long id){
		return accountRepository.findById(id);
	}
		
}
