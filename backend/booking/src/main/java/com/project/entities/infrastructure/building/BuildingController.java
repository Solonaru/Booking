package com.project.entities.infrastructure.building;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.IEntityController;

@RestController
@RequestMapping("/building")
@CrossOrigin(origins = "http://localhost:4200")
public class BuildingController implements IEntityController<Building, Long> {

	@Autowired
	private IBuildingService buildingService;

	public Optional<Building> findById(@PathVariable("id") Long buildingId) {
		return buildingService.findById(buildingId);
	}

	public List<Building> getAll() {
		return buildingService.findAll();
	}

	public void insert(@RequestBody Building building) {
		buildingService.insert(building);
	}

	public void update(@RequestBody Building building) {
		buildingService.update(building);
	}

	public void delete(@PathVariable("id") Long buildingId) {
		buildingService.deleteById(buildingId);
	}

}
