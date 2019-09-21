package com.project.entities.infrastructure.building;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService implements IBuildingService {
	
	@Autowired
	private IBuildingRepository buildingRepository;

	public Optional<Building> findById(Long buildingId) {
		return buildingRepository.findById(buildingId);
	}

	public List<Building> findAll() {
		return (List<Building>) buildingRepository.findAll();
	}

	public void insert(Building building) {
		buildingRepository.save(building);
	}

	public void update(Building building) {
		buildingRepository.save(building);
	}

	public void deleteById(Long buildingId) {
		buildingRepository.deleteById(buildingId);
	}

}
