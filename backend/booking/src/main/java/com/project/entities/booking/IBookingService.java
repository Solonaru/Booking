package com.project.entities.booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBookingService {

	public Optional<Booking> findById(Long entityId);

	public List<Booking> findAll();

	public Booking insert(Booking entity);

	public Booking update(Booking entity);

	public void deleteById(Long entityId);

	public List<Booking> findAllByRoomId(Long roomId);

	public List<Booking> findAllByRoomIdAndDate(Long roomId, LocalDate date);

}
