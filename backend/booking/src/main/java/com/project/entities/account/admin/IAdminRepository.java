package com.project.entities.account.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends CrudRepository<Admin, Long> {

}
