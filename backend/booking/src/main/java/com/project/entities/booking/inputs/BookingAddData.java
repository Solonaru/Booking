package com.project.entities.booking.inputs;

import java.time.LocalDateTime;

public class BookingAddData {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long roomId;

	/* ----- GETTERS & SETTERS ----- */
	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}
	
	public Long getRoomId() {
		return roomId;
	}

}
