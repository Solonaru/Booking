package com.project.entities.infrastructure.floor;

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
@RequestMapping("/floor")
@CrossOrigin(origins = "http://localhost:4200")
public class FloorController implements IEntityController<Floor, Long> {

	@Autowired
	private IFloorService floorService;

	public Optional<Floor> findById(@PathVariable("id") Long floorId) {
		return floorService.findById(floorId);
	}

	public List<Floor> getAll() {
		return floorService.findAll();
	}

	public void insert(@RequestBody Floor floor) {
		floorService.insert(floor);
	}

	public void update(@RequestBody Floor floor) {
		floorService.update(floor);
	}

	public void delete(@PathVariable("id") Long floorId) {
		floorService.deleteById(floorId);
	}

}
