package com.project.entities.infrastructure.floor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.entities.infrastructure.building.Building;
import com.project.entities.infrastructure.room.Room;

@Entity
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "floor_generator")
	@SequenceGenerator(name = "floor_generator", sequenceName = "floor_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "nr", updatable = false, nullable = false)
	private String nr;

	@JsonIgnoreProperties(value = "floors")
	@ManyToOne
	@JoinColumn(name = "building", updatable = false, nullable = false)
	private Building building;

	@JsonIgnoreProperties(value = "floor")
	@OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Room> rooms;

	/* ----- CONSTRUCTORS ----- */
	public Floor() {
		super();
	}

	public Floor(String nr) {
		super();
		this.nr = nr;
		this.rooms = new HashSet<Room>();
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getNr() {
		return nr;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	/* ----- METHODS ----- */
	public void addRoom(Room room) {
		this.rooms.add(room);
		room.setFloor(this);
	}

	public void removeRoom(Room room) {
		this.rooms.remove(room);
		room.setFloor(null);
	}

}
