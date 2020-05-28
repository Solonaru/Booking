package com.project.entities.booking;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.booking.inputs.BookingAddData;
import com.project.entities.booking.inputs.BookingFetchData;
import com.project.entities.infrastructure.room.IRoomService;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private IRoomService roomService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Booking> findById(@PathVariable("id") Long bookingId) {
		return bookingService.findById(bookingId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Booking> getAll() {
		return bookingService.findAll();
	}

	@RequestMapping(value = "/all/room/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Booking> getAllByRoomId(@PathVariable("id") Long roomId) {
		return bookingService.findAllByRoomId(roomId);
	}

	@RequestMapping(value = "/all/room/data", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Booking> getAllByRoomIdAndDate(@RequestBody BookingFetchData data) {
		return bookingService.findAllByRoomIdAndDate(data.getRoomId(), data.getDate());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@RequestBody BookingAddData data) {

		if ((null != data) && (null != data.getStartTime()) && (null != data.getEndTime()) && (0 != data.getRoomId())) {
			Booking result;

			// TODO: Get booking reason
			Booking booking = new Booking(data.getStartTime(), data.getEndTime(), "new booking");

			booking.setRoom(this.roomService.findById(data.getRoomId()).get());

			result = bookingService.insert(booking);
			
			if(null != result) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Booking booking) {
		bookingService.update(booking);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("id") Long bookingId) {
		bookingService.deleteById(bookingId);
	}

}
