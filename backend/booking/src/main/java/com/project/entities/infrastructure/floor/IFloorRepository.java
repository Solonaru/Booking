package com.project.entities.infrastructure.floor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFloorRepository extends CrudRepository<Floor, Long> {

}
