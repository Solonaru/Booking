package com.project.entities.account.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {
	
	@Autowired
	private IAdminRepository adminRepository;

	public Optional<Admin> findById(Long adminId) {
		return adminRepository.findById(adminId);
	}

	public List<Admin> findAll() {
		return (List<Admin>) adminRepository.findAll();
	}

	public void insert(Admin admin) {
		adminRepository.save(admin);
	}

	public void update(Admin admin) {
		adminRepository.save(admin);
	}

	public void deleteById(Long adminId) {
		adminRepository.deleteById(adminId);
	}

}
