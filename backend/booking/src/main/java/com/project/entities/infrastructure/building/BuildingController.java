package com.project.entities.infrastructure.building;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.IEntityController;

@RestController
@RequestMapping("/building")
@CrossOrigin(origins = "http://localhost:4200")
public class BuildingController implements IEntityController<Building, Long> {

	@Autowired
	private IBuildingService buildingService;

	@RequestMapping(value = "/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Building> findById(@PathVariable("buildingId") Long buildingId) {
		return buildingService.findById(buildingId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Building> getAll() {
		return buildingService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Building building) {
		buildingService.insert(building);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Building building) {
		buildingService.update(building);
	}

	@RequestMapping(value = "/delete/{buildingId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("buildingId") Long buildingId) {
		buildingService.deleteById(buildingId);
	}
}
