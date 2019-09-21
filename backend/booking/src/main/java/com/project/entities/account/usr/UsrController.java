package com.project.entities.account.usr;

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
@RequestMapping("/usr")
@CrossOrigin(origins = "http://localhost:4200")
public class UsrController implements IEntityController<Usr, Long> {
	
	@Autowired
	private IUsrService usrService;
	
	@RequestMapping(value = "/{usrId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Usr> findById(@PathVariable("usrId") Long usrId) {
		return usrService.findById(usrId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Usr> getAll() {
		return usrService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Usr usr) {
		usrService.insert(usr);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Usr usr) {
		usrService.update(usr);
	}

	@RequestMapping(value = "/delete/{usrId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("usrId") Long usrId) {
		usrService.deleteById(usrId);
	}
	
}
