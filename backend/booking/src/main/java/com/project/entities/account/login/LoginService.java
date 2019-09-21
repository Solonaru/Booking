package com.project.entities.account.login;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
	
	@Autowired
	private ILoginRepository loginRepository;

	public Optional<Login> findById(String loginId) {
		return loginRepository.findById(loginId);
	}

	public List<Login> findAll() {
		return (List<Login>) loginRepository.findAll();
	}

	public void insert(Login login) {
		loginRepository.save(login);
	}

	public void update(Login login) {
		loginRepository.save(login);
	}

	public void deleteById(String loginId) {
		loginRepository.deleteById(loginId);
	}

}
