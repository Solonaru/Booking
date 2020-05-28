package com.project.entities.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends CrudRepository<Account, Long> {

}
