package com.project.entities.infrastructure.room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends CrudRepository<Room, Long> {

}
