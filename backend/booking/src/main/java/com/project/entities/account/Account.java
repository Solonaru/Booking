package com.project.entities.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.entities.account.login.Login;
import com.project.entities.account.personaldata.PersonalData;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_generator")
	@SequenceGenerator(name = "usr_generator", sequenceName = "usr_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;

	@JsonIgnoreProperties(value = "account")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Login login;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "personal_data")
	private PersonalData personalData;

	/* ----- CONSTRUCTORS ----- */
	protected Account() {
		super();
	}

	protected Account(String name, String surname, String username, String password) {
		super();
		this.login = new Login(username, password, this);
		this.personalData = new PersonalData(name, surname);
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getName() {
		return this.personalData.getName();
	}

	public void setName(String name) {
		this.personalData.setName(name);
	}

	public String getSurname() {
		return this.personalData.getSurname();
	}

	public void setSurname(String surname) {
		this.personalData.setSurname(surname);
	}

}
