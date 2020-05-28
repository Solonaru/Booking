package com.project.entities.account.usr;

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
@RequestMapping("/usr")
@CrossOrigin(origins = "http://localhost:4200")
public class UsrController implements IEntityController<Usr, Long> {

	@Autowired
	private IUsrService usrService;

	public Optional<Usr> findById(@PathVariable("id") Long usrId) {
		return usrService.findById(usrId);
	}

	public List<Usr> getAll() {
		return usrService.findAll();
	}

	public void insert(@RequestBody Usr usr) {
		usrService.insert(usr);
	}

	public void update(@RequestBody Usr usr) {
		usrService.update(usr);
	}

	public void delete(@PathVariable("id") Long usrId) {
		usrService.deleteById(usrId);
	}

}
