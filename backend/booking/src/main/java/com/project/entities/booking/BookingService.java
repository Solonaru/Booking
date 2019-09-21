package com.project.entities.booking;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void insert(Booking booking) {
		bookingRepository.save(booking);
	}

	public void update(Booking booking) {
		bookingRepository.save(booking);
	}

	public void deleteById(Long bookingId) {
		bookingRepository.deleteById(bookingId);
	}

}
