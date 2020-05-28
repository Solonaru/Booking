package com.project.entities.infrastructure.room;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.entities.booking.Booking;
import com.project.entities.infrastructure.floor.Floor;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
	@SequenceGenerator(name = "room_generator", sequenceName = "room_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnoreProperties(value = "rooms")
	@ManyToOne
	@JoinColumn(name = "floor", updatable = false, nullable = false)
	private Floor floor;

	@JsonIgnoreProperties(value = "room")
	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Booking> bookings;

	/* ----- CONSTRUCTORS ----- */
	public Room() {
		super();
	}

	public Room(String name) {
		super();
		this.name = name;
		this.bookings = new HashSet<Booking>();
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	/* ----- METHODS ----- */
	public String getBuildingName() {
		return this.getFloor().getBuilding().getName();
	}

	public String getFloorNr() {
		return this.getFloor().getNr();
	}

}
