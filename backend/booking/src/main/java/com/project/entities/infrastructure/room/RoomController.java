package com.project.entities.infrastructure.room;

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
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController implements IEntityController<Room, Long> {
	
	@Autowired
	private IRoomService roomService;
	
	@RequestMapping(value = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Room> findById(@PathVariable("roomId") Long roomId) {
		return roomService.findById(roomId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Room> getAll() {
		return roomService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Room room) {
		roomService.insert(room);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Room room) {
		roomService.update(room);
	}

	@RequestMapping(value = "/delete/{roomId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("roomId") Long roomId) {
		roomService.deleteById(roomId);
	}
}
