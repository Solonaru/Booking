package com.project.entities.infrastructure.floor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloorService implements IFloorService {
	
	@Autowired
	private IFloorRepository floorRepository;

	public Optional<Floor> findById(Long floorId) {
		return floorRepository.findById(floorId);
	}

	public List<Floor> findAll() {
		return (List<Floor>) floorRepository.findAll();
	}

	public void insert(Floor floor) {
		floorRepository.save(floor);
	}

	public void update(Floor floor) {
		floorRepository.save(floor);
	}

	public void deleteById(Long floorId) {
		floorRepository.deleteById(floorId);
	}

}
