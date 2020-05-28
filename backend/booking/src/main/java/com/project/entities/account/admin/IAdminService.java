package com.project.entities.account.admin;

import org.springframework.data.repository.NoRepositoryBean;

import com.project.entities.IEntityService;

@NoRepositoryBean
public interface IAdminService extends IEntityService<Admin, Long> {

}
