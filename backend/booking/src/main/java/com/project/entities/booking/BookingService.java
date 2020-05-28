package com.project.entities.booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.utils.DataLogger;

@Service
public class BookingService implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepository;

	public Optional<Booking> findById(Long bookingId) {
		return bookingRepository.findById(bookingId);
	}

	public List<Booking> findAll() {
		return (List<Booking>) bookingRepository.findAll();
	}

	public Booking insert(Booking booking) {
		Booking result = null;

		if (isBookingAvailable(booking)) {
			result = bookingRepository.save(booking);
		} else {
			// TODO Handle properly
			DataLogger.printError("Couldn't book room: " + booking.getRoom().getName()
					+ ". Booking already present for the specified interval.");
		}

		return result;
	}

	public Booking update(Booking booking) {
		return bookingRepository.save(booking);
	}

	public void deleteById(Long bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	/* ----- CUSTOM METHODS ----- */
	public List<Booking> findAllByRoomId(Long roomId) {
		return bookingRepository.findAllByRoom_Id(roomId);
	}

	public List<Booking> findAllByRoomIdAndDate(Long roomId, LocalDate date) {
		return bookingRepository.findAllByRoom_IdAndDate(roomId, date);
	}

	/* ----- PRIVATE LOCAL METHODS ----- */
	private boolean isBookingAvailable(Booking booking) {
		/*
		 * EXPLANATION: We look if bookings are already made in the specified interval,
		 * if not then the booking is available.
		 * 
		 * NOTE: The end time of a booking may coincide with the start time of another
		 * booking.
		 */
		return bookingRepository.findAllByRoomAndDateAndTime(booking.getRoom(), booking.getDate(),
				booking.getStartTime(), booking.getEndTime()).isEmpty();
	}

}
