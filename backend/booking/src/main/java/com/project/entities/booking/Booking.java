package com.project.entities.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.entities.account.usr.Usr;
import com.project.entities.infrastructure.room.Room;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_generator")
	@SequenceGenerator(name = "booking_generator", sequenceName = "booking_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "startTime", updatable = false, nullable = false)
	private LocalTime startTime;

	@Column(name = "endTime", updatable = false, nullable = false)
	private LocalTime endTime;

	@Column(name = "date", updatable = false, nullable = false)
	private LocalDate date;

	@Column(name = "reservationDate", updatable = false, nullable = false)
	private LocalDateTime reservationDate;

	@Column(name = "reason", updatable = false)
	private String reason;

	@JsonIgnoreProperties(value = "bookings")
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room")
	private Room room;

	@JsonIgnoreProperties(value = "bookings")
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "usr")
	private Usr usr;

	/* ----- CONSTRUCTORS ----- */
	public Booking() {
		super();
	}

	public Booking(LocalTime startTime, LocalTime endTime, LocalDate date, LocalDateTime reservationDate,
			String reason) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.reservationDate = reservationDate;
		this.reason = reason;
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
		this.room.getBookings().add(this);
	}

	public Usr getUsr() {
		return usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
		this.usr.getBookings().add(this);
	}

}
