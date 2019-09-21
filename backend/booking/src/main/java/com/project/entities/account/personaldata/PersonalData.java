package com.project.entities.account.personaldata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class PersonalData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_data_generator")
	@SequenceGenerator(name = "personal_data_generator", sequenceName = "personal_data_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	/* ----- CONSTRUCTORS ----- */
	public PersonalData() {
		super();
	}

	public PersonalData(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
