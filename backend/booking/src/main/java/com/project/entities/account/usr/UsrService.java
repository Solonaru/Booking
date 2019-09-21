package com.project.entities.account.usr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsrService implements IUsrService {
	
	@Autowired
	private IUsrRepository usrRepository;

	public Optional<Usr> findById(Long usrId) {
		return usrRepository.findById(usrId);
	}

	public List<Usr> findAll() {
		return (List<Usr>) usrRepository.findAll();
	}

	public void insert(Usr usr) {
		usrRepository.save(usr);
	}

	public void update(Usr usr) {
		usrRepository.save(usr);
	}

	public void deleteById(Long usrId) {
		usrRepository.deleteById(usrId);
	}

}
