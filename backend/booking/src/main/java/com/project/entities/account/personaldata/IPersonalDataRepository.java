package com.project.entities.account.personaldata;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonalDataRepository extends CrudRepository<PersonalData, Long> {

}
