package com.project.entities.account.login;

import org.springframework.data.repository.NoRepositoryBean;

import com.project.entities.IEntityService;

@NoRepositoryBean
public interface ILoginService extends IEntityService<Login, String> {

}
