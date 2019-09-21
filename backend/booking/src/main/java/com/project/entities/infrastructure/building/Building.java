package com.project.entities.infrastructure.building;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.entities.infrastructure.floor.Floor;

@Entity
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bulding_generator")
	@SequenceGenerator(name = "building_generator", sequenceName = "building_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "address", nullable = false)
	private String address;

	@JsonIgnoreProperties(value = "building")
	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Floor> floors;

	/* ----- CONSTRUCTORS ----- */
	public Building() {
		super();
	}

	public Building(String name, String address) {
		super();
		this.name = name;
		this.address = address;
		this.floors = new HashSet<Floor>();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Floor> getFloors() {
		return floors;
	}

	public void setFloors(Set<Floor> floors) {
		this.floors = floors;
	}

	/* ----- METHODS ----- */
	public void addFloor(Floor floor) {
		this.floors.add(floor);
		floor.setBuilding(this);
	}

	public void removeFloor(Floor floor) {
		this.floors.remove(floor);
		floor.setBuilding(null);
	}

}
