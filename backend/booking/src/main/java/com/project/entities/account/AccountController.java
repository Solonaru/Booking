package com.project.entities.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.IEntityController;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController implements IEntityController<Account, Long> {

	@Autowired
	private IAccountService accountService;

	public Optional<Account> findById(@PathVariable("id") Long accountId) {
		return accountService.findById(accountId);
	}

	public List<Account> getAll() {
		return accountService.findAll();
	}

	public void insert(@RequestBody Account account) {
		accountService.insert(account);
	}

	public void update(@RequestBody Account account) {
		accountService.update(account);
	}

	public void delete(@PathVariable("id") Long accountId) {
		accountService.deleteById(accountId);
	}
}
