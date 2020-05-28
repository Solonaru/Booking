package com.project.entities.account;

import org.springframework.data.repository.NoRepositoryBean;

import com.project.entities.IEntityService;

@NoRepositoryBean
public interface IAccountService extends IEntityService<Account, Long> {

}
