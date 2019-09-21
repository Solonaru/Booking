package com.project.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.project.entities.account.Account;
import com.project.entities.account.IAccountService;
import com.project.entities.account.admin.Admin;
import com.project.entities.account.usr.IUsrService;
import com.project.entities.account.usr.Usr;
import com.project.entities.booking.Booking;
import com.project.entities.booking.IBookingService;
import com.project.entities.infrastructure.building.Building;
import com.project.entities.infrastructure.building.IBuildingService;
import com.project.entities.infrastructure.floor.Floor;
import com.project.entities.infrastructure.room.IRoomService;
import com.project.entities.infrastructure.room.Room;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private DataLogger logger;

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IUsrService usrService;
	@Autowired
	private IBuildingService buildingService;
	@Autowired
	private IRoomService roomService;
	@Autowired
	private IBookingService bookingService;

	/* ----- INITIALIZER ----- */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadData();
	}

	/* ----- METHODS ----- */
	private void loadData() {
		logger.printInfo("Starting data loading...");

		createAndPersistAccounts();
		createAndPersistInfrastructure();
		createAndPersistBookings();

		logger.printInfo("Data successfully loaded.");
	}

	private void createAndPersistAccounts() {
		Account usr1 = new Usr("Agape", "Solonaru", "Agape", "08102004");
		Account usr2 = new Usr("Lilian", "Solonaru", "Lilian", "12061998");
		Account usr3 = new Usr("Viorel", "Solonaru", "Viorel", "17041996");
		Account usr4 = new Admin("Anastasia", "Solonaru", "Anastasia", "02022006");

		this.accountService.insert(usr1);
		this.accountService.insert(usr2);
		this.accountService.insert(usr3);

		this.accountService.deleteById(1L);

		this.accountService.insert(usr4);
	}

	private void createAndPersistInfrastructure() {
		Room room001 = new Room("B301");
		Room room002 = new Room("B305");
		Room room003 = new Room("B307");
		Room room004 = new Room("B320");
		Room room101 = new Room("B1");
		Room room102 = new Room("B402");
		Room room103 = new Room("B405");
		Room room201 = new Room("B5");
		Room room202 = new Room("B3");

		Floor floor0 = new Floor(0);
		floor0.addRoom(room001);
		floor0.addRoom(room002);
		floor0.addRoom(room003);
		floor0.addRoom(room004);
		Floor floor1 = new Floor(1);
		floor1.addRoom(room101);
		floor1.addRoom(room102);
		floor1.addRoom(room103);
		Floor floor2 = new Floor(2);
		floor2.addRoom(room201);
		floor2.addRoom(room202);

		Building bulding = new Building("FEAA", "Str. FEAA nr.1");
		bulding.addFloor(floor0);
		bulding.addFloor(floor1);
		bulding.addFloor(floor2);

		this.buildingService.insert(bulding);
	}

	private void createAndPersistBookings() {
		List<Usr> usrs = this.usrService.findAll();
		List<Room> rooms = this.roomService.findAll();

		Booking booking1 = new Booking(LocalTime.of(10, 0), LocalTime.of(12, 0), LocalDate.of(2019, 10, 12),
				LocalDateTime.of(2019, 10, 11, 16, 32), "Meeting");
		booking1.setRoom(rooms.get(0));
		booking1.setUsr(usrs.get(1));
		Booking booking2 = new Booking(LocalTime.of(12, 0), LocalTime.of(14, 0), LocalDate.of(2019, 10, 12),
				LocalDateTime.of(2019, 10, 11, 16, 37), "Students evaluation");
		booking2.setRoom(rooms.get(0));
		booking2.setUsr(usrs.get(1));
		Booking booking3 = new Booking(LocalTime.of(8, 0), LocalTime.of(12, 0), LocalDate.of(2019, 10, 14),
				LocalDateTime.of(2019, 10, 13, 11, 22), "Professors Meeting");
		booking3.setRoom(rooms.get(5));
		booking3.setUsr(usrs.get(0));
		Booking booking4 = new Booking(LocalTime.of(9, 0), LocalTime.of(10, 0), LocalDate.of(2019, 10, 14),
				LocalDateTime.of(2019, 10, 13, 11, 35), "Meeting");
		booking4.setRoom(rooms.get(7));
		booking4.setUsr(usrs.get(0));

		this.bookingService.insert(booking1);
		this.bookingService.insert(booking2);
		this.bookingService.insert(booking3);
		this.bookingService.insert(booking4);
	}

}
