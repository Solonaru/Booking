package com.project.entities.infrastructure.building;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuildingRepository extends CrudRepository<Building, Long> {

}
