package com.project.entities.infrastructure.room;

import org.springframework.data.repository.NoRepositoryBean;

import com.project.entities.IEntityService;

@NoRepositoryBean
public interface IRoomService extends IEntityService<Room, Long> {

}
