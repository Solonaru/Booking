package com.project.entities.infrastructure.room;

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
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController implements IEntityController<Room, Long> {

	@Autowired
	private IRoomService roomService;

	public Optional<Room> findById(@PathVariable("id") Long roomId) {
		return roomService.findById(roomId);
	}

	public List<Room> getAll() {
		return roomService.findAll();
	}

	public void insert(@RequestBody Room room) {
		roomService.insert(room);
	}

	public void update(@RequestBody Room room) {
		roomService.update(room);
	}

	public void delete(@PathVariable("id") Long roomId) {
		roomService.deleteById(roomId);
	}

}
