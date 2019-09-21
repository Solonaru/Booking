package com.project.entities.infrastructure.floor;

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
@RequestMapping("/floor")
@CrossOrigin(origins = "http://localhost:4200")
public class FloorController implements IEntityController<Floor, Long> {
	
	@Autowired
	private IFloorService floorService;
	
	@RequestMapping(value = "/{floorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Floor> findById(@PathVariable("floorId") Long floorId) {
		return floorService.findById(floorId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Floor> getAll() {
		return floorService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Floor floor) {
		floorService.insert(floor);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Floor floor) {
		floorService.update(floor);
	}

	@RequestMapping(value = "/delete/{floorId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("floorId") Long floorId) {
		floorService.deleteById(floorId);
	}
}
