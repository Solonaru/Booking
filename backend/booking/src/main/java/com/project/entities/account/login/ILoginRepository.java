package com.project.entities.account.login;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoginRepository extends CrudRepository<Login, String> {

}
