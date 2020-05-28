package com.project.entities.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.infrastructure.room.Room;

@Repository
public interface IBookingRepository extends CrudRepository<Booking, Long> {

	public List<Booking> findAllByRoom_Id(Long roomId);

	public List<Booking> findAllByRoom_IdAndDate(Long roomId, LocalDate date);

	@Query("SELECT b FROM Booking b WHERE (b.room = :room AND b.date = :date) AND"
			+ "((startTime >= :startTime AND startTime < :endTime) OR (endTime > :startTime AND endTime <= :endTime))")
	public List<Booking> findAllByRoomAndDateAndTime(@Param("room") Room room, @Param("date") LocalDate date,
			@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}
