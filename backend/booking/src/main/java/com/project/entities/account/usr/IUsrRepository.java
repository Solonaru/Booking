package com.project.entities.account.usr;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsrRepository extends CrudRepository<Usr, Long> {

}
