package com.project.entities.account.admin;

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
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController implements IEntityController<Admin, Long> {

	@Autowired
	private IAdminService adminService;

	public Optional<Admin> findById(@PathVariable("id") Long adminId) {
		return adminService.findById(adminId);
	}

	public List<Admin> getAll() {
		return adminService.findAll();
	}

	public void insert(@RequestBody Admin admin) {
		adminService.insert(admin);
	}

	public void update(@RequestBody Admin admin) {
		adminService.update(admin);
	}

	public void delete(@PathVariable("id") Long adminId) {
		adminService.deleteById(adminId);
	}

}
