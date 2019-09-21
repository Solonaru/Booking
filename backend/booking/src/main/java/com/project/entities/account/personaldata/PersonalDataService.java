package com.project.entities.account.personaldata;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalDataService implements IPersonalDataService {
	
	@Autowired
	private IPersonalDataRepository personalDataRepository;

	public Optional<PersonalData> findById(Long personalDataId) {
		return personalDataRepository.findById(personalDataId);
	}

	public List<PersonalData> findAll() {
		return (List<PersonalData>) personalDataRepository.findAll();
	}

	public void insert(PersonalData personalData) {
		personalDataRepository.save(personalData);
	}

	public void update(PersonalData personalData) {
		personalDataRepository.save(personalData);
	}

	public void deleteById(Long personalDataId) {
		personalDataRepository.deleteById(personalDataId);
	}

}
