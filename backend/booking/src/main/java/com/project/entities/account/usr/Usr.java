package com.project.entities.account.usr;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.entities.account.Account;
import com.project.entities.booking.Booking;

@Entity
public class Usr extends Account {

	@JsonIgnoreProperties(value = "usr")
	@OneToMany(mappedBy = "usr", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Booking> bookings;

	/* ----- CONSTRUCTORS ----- */
	public Usr() {
		super();
	}

	public Usr(String name, String surname, String username, String password) {
		super(name, surname, username, password);
		this.bookings = new HashSet<Booking>();
	}

	/* ----- GETTERS & SETTERS ----- */
	public Set<Booking> getBookings() {
		return bookings;
	}

}
