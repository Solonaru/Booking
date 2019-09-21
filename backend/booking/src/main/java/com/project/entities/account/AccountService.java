package com.project.entities.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
	
	@Autowired
	private IAccountRepository accountRepository;

	public Optional<Account> findById(Long accountId) {
		return accountRepository.findById(accountId);
	}

	public List<Account> findAll() {
		return (List<Account>) accountRepository.findAll();
	}

	public void insert(Account account) {
		accountRepository.save(account);
	}

	public void update(Account account) {
		accountRepository.save(account);
	}

	public void deleteById(Long accountId) {
		accountRepository.deleteById(accountId);
	}

}
