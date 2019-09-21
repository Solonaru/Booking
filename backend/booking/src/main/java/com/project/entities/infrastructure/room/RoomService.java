package com.project.entities.infrastructure.room;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService implements IRoomService {
	
	@Autowired
	private IRoomRepository roomRepository;

	public Optional<Room> findById(Long roomId) {
		return roomRepository.findById(roomId);
	}

	public List<Room> findAll() {
		return (List<Room>) roomRepository.findAll();
	}

	public void insert(Room room) {
		roomRepository.save(room);
	}

	public void update(Room room) {
		roomRepository.save(room);
	}

	public void deleteById(Long roomId) {
		roomRepository.deleteById(roomId);
	}

}
