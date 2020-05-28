package com.project.entities.account.personaldata;

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
@RequestMapping("/personal_data")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonalDataController implements IEntityController<PersonalData, Long> {

	@Autowired
	private IPersonalDataService personalDataService;

	public Optional<PersonalData> findById(@PathVariable("id") Long personalDataId) {
		return personalDataService.findById(personalDataId);
	}

	public List<PersonalData> getAll() {
		return personalDataService.findAll();
	}

	public void insert(@RequestBody PersonalData personalData) {
		personalDataService.insert(personalData);
	}

	public void update(@RequestBody PersonalData personalData) {
		personalDataService.update(personalData);
	}

	public void delete(@PathVariable("id") Long personalDataId) {
		personalDataService.deleteById(personalDataId);
	}

}
