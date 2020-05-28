package com.project.entities.account.personaldata;

import org.springframework.data.repository.NoRepositoryBean;

import com.project.entities.IEntityService;

@NoRepositoryBean
public interface IPersonalDataService extends IEntityService<PersonalData, Long> {

}
