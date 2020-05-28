package com.project.entities.infrastructure.floor;

import org.springframework.data.repository.NoRepositoryBean;

import com.project.entities.IEntityService;

@NoRepositoryBean
public interface IFloorService extends IEntityService<Floor, Long> {

}
