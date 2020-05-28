package com.project.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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
		DataLogger.printInfo("Starting data loading...");

		createAndPersistAccounts();
		createAndPersistInfrastructure();
		createAndPersistBookings();

		DataLogger.printInfo("Data successfully loaded.");
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
		this.persistBloculB();
		this.persistBloculC();
	}

	private void persistBloculB() {
		Room room001 = new Room("B301");
		Room room002 = new Room("B305");
		Room room003 = new Room("B307");
		Room room004 = new Room("B320");
		Room room101 = new Room("B1");
		Room room102 = new Room("B402");
		Room room103 = new Room("B405");
		Room room201 = new Room("B5");
		Room room202 = new Room("B3");

		Floor floor0 = new Floor("GF");
		floor0.addRoom(room001);
		floor0.addRoom(room002);
		floor0.addRoom(room003);
		floor0.addRoom(room004);
		Floor floor1 = new Floor("1");
		floor1.addRoom(room101);
		floor1.addRoom(room102);
		floor1.addRoom(room103);
		Floor floor2 = new Floor("2");
		floor2.addRoom(room201);
		floor2.addRoom(room202);

		Building bulding = new Building("Blocul B", "Str. FEAA nr.1");
		bulding.addFloor(floor0);
		bulding.addFloor(floor1);
		bulding.addFloor(floor2);

		this.buildingService.insert(bulding);
	}

	private void persistBloculC() {
		Room room001 = new Room("C312");
		Room room002 = new Room("C305");
		Room room003 = new Room("C307");
		Room room004 = new Room("C320");
		Room room101 = new Room("C405");
		Room room102 = new Room("C1");
		Room room201 = new Room("C5");
		Room room202 = new Room("C3");
		Room room203 = new Room("C4");
		Room room204 = new Room("C503");
		Room room205 = new Room("C513");

		Floor floor0 = new Floor("GF");
		floor0.addRoom(room001);
		floor0.addRoom(room002);
		floor0.addRoom(room003);
		floor0.addRoom(room004);
		Floor floor1 = new Floor("1");
		floor1.addRoom(room101);
		floor1.addRoom(room102);
		Floor floor2 = new Floor("2");
		floor2.addRoom(room201);
		floor2.addRoom(room202);
		floor2.addRoom(room203);
		floor2.addRoom(room204);
		floor2.addRoom(room205);

		Building bulding = new Building("Blocul C", "Str. FEAA nr.13");
		bulding.addFloor(floor0);
		bulding.addFloor(floor1);
		bulding.addFloor(floor2);

		this.buildingService.insert(bulding);
	}

	private void createAndPersistBookings() {
		int day = 11;
		int month = 03;

		List<Usr> usrs = this.usrService.findAll();
		List<Room> rooms = this.roomService.findAll();

		Booking booking1 = new Booking(LocalDateTime.of(2020, month, day, 10, 0),
				LocalDateTime.of(2020, month, day, 12, 0), "Meeting");
		booking1.setRoom(this.getRnFromList(rooms));
		booking1.setUsr(this.getRnFromList(usrs));
		Booking booking2 = new Booking(LocalDateTime.of(2020, month, day, 12, 0),
				LocalDateTime.of(2020, month, day, 14, 0), "Students evaluation");
		booking2.setRoom(this.getRnFromList(rooms));
		booking2.setUsr(this.getRnFromList(usrs));
		Booking booking3 = new Booking(LocalDateTime.of(2020, month, day + 1, 8, 0),
				LocalDateTime.of(2020, month, day + 1, 12, 0), "Professors Meeting");
		booking3.setRoom(this.getRnFromList(rooms));
		booking3.setUsr(this.getRnFromList(usrs));
		Booking booking4 = new Booking(LocalDateTime.of(2020, month, day + 1, 9, 0),
				LocalDateTime.of(2020, month, day + 1, 10, 0), "Meeting");
		booking4.setRoom(this.getRnFromList(rooms));
		booking4.setUsr(this.getRnFromList(usrs));

		Booking booking5 = new Booking(LocalDateTime.of(2020, month, day, 10, 0),
				LocalDateTime.of(2020, month, day, 12, 0), "Professor's Meeting");
		booking5.setRoom(this.getRnFromList(rooms));
		booking5.setUsr(this.getRnFromList(usrs));
		Booking booking6 = new Booking(LocalDateTime.of(2020, month, day + 2, 12, 0),
				LocalDateTime.of(2020, month, day + 2, 14, 0), "Students evaluation");
		booking6.setRoom(this.getRnFromList(rooms));
		booking6.setUsr(this.getRnFromList(usrs));
		Booking booking7 = new Booking(LocalDateTime.of(2020, month, day + 2, 8, 0),
				LocalDateTime.of(2020, month, day + 2, 12, 0), "Meeting");
		booking7.setRoom(this.getRnFromList(rooms));
		booking7.setUsr(this.getRnFromList(usrs));
		Booking booking8 = new Booking(LocalDateTime.of(2020, month, day, 9, 0),
				LocalDateTime.of(2020, month, day, 10, 0), "Meeting");
		booking8.setRoom(this.getRnFromList(rooms));
		booking8.setUsr(this.getRnFromList(usrs));

		this.bookingService.insert(booking1);
		this.bookingService.insert(booking2);
		this.bookingService.insert(booking3);
		this.bookingService.insert(booking4);
		this.bookingService.insert(booking5);
		this.bookingService.insert(booking6);
		this.bookingService.insert(booking7);
		this.bookingService.insert(booking8);
	}

	private Integer getRnInteger(Integer min, Integer max) {
		return new Random().nextInt(max - min + 1) + min;
	}

	private <T> T getRnFromList(List<T> elements) {
		return elements.get(getRnInteger(0, elements.size() - 1));
	}

}
