package com.project.entities.infrastructure.building;

import org.springframework.data.repository.NoRepositoryBean;

import com.project.entities.IEntityService;

@NoRepositoryBean
public interface IBuildingService extends IEntityService<Building, Long> {

}
