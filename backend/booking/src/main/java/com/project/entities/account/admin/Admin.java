package com.project.entities.account.admin;

import javax.persistence.Entity;

import com.project.entities.account.Account;

@Entity
public class Admin extends Account {

	/* ----- CONSTRUCTORS ----- */
	public Admin() {
		super();
	}

	public Admin(String name, String surname, String username, String password) {
		super(name, surname, username, password);
	}

}
