package com.project.entities.booking.inputs;

import java.time.LocalDate;

public class BookingFetchData {
	private LocalDate date;
	private Long roomId;

	/* ----- GETTERS & SETTERS ----- */
	public LocalDate getDate() {
		return date;
	}
	
	public Long getRoomId() {
		return roomId;
	}

}
