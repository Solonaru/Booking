package com.project.entities.account.personaldata;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.IEntityController;

@RestController
@RequestMapping("/personal_data")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonalDataController implements IEntityController<PersonalData, Long> {
	
	@Autowired
	private IPersonalDataService personalDataService;
	
	@RequestMapping(value = "/{personalDataId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<PersonalData> findById(@PathVariable("personalDataId") Long personalDataId) {
		return personalDataService.findById(personalDataId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonalData> getAll() {
		return personalDataService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody PersonalData personalData) {
		personalDataService.insert(personalData);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody PersonalData personalData) {
		personalDataService.update(personalData);
	}

	@RequestMapping(value = "/delete/{personalDataId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("personalDataId") Long personalDataId) {
		personalDataService.deleteById(personalDataId);
	}
}
