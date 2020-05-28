package com.project.entities.account.login;

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
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController implements IEntityController<Login, String> {

	@Autowired
	private ILoginService loginService;

	public Optional<Login> findById(@PathVariable("id") String loginId) {
		return loginService.findById(loginId);
	}

	public List<Login> getAll() {
		return loginService.findAll();
	}

	public void insert(@RequestBody Login login) {
		loginService.insert(login);
	}

	public void update(@RequestBody Login login) {
		loginService.update(login);
	}

	public void delete(@PathVariable("id") String loginId) {
		loginService.deleteById(loginId);
	}

}
