package A4;
//Nikoo Sarraf
//2606767310

import java.util.NoSuchElementException;

public class Hotel {
	private String name;
	private Room[] rooms;
	private Reservation[] arrayReservation;

	//constructor which creates a new hotel
	public Hotel(String hotelName, Room[] room) {
		this.name = hotelName;
		this.rooms = new Room[room.length];

		for (int i =0; i < rooms.length; i++) {
			rooms[i] = room[i];
		}

		//creates a new reservation array with the length equal to the number of hotels in the string
		this.arrayReservation = new Reservation[rooms.length];
	}

	//searches through an array and adds a reservation to the first open room
	private void addReservation(Reservation reservation) {
		for (int i = 0; i < arrayReservation.length; i++) {
			if (arrayReservation[i] == null) {
				arrayReservation[i] = reservation;
				break;
			}
		}
	}

	//helper method which finds the first available reservation in the reservation array
	//and removes the reservation and updates availability
	public int findFirstRez(String name, Room type) {
		for (int i = 0; i < this.arrayReservation.length; i++) {
			Reservation rez = this.arrayReservation[i];
			Room rezRoom = rez.getRoom();
			if (name.equals(arrayReservation[i].getName()) && (type.getType()).equals(rezRoom.getType())) {
				this.arrayReservation[i].getRoom().changeAvailability();
				this.arrayReservation[i] = null;
				return i;
			}
		}
		return 0;
	}

	//this method removes the reservation unless there is no reservation
	private void removeReservation(String name, Room type) {
		if (findFirstRez(name, type) == 0) {
			throw new NoSuchElementException("There is no reservation made under this name");
		}
		else {
			findFirstRez(name, type);
		}
	}

	//this method creates the reservation if there is an available room
	public void createReservation(String name, String type) {
		//System.out.println(rooms);
		Room available = Room.findAvailableRoom(this.rooms, type);
		if(available == null ) {
			available.changeAvailability();
			Reservation newRez = new Reservation(name, available);
			addReservation(newRez);
			System.out.println("You have successfully made a reservation.");
		}
		else {
			System.out.println("There is no room available at this moment.");

		}
	}

	//uses removeReservation to take the reservation out of the array
	public void cancelReservation(String name, Room type) {
		try {
			this.removeReservation(name, type);
			System.out.println("You have successfully cancelled your reservation");
		}
		catch (Exception e) {
			System.out.println("No reservation has been made under this name");
		}
	}

	//gets name and room type and compares to find the matching reservation
	//uses getPrice to add up the amounts of all bookings
	public void printInvoice(String name) {
		double amount = 0;
		for (int i =0; i < arrayReservation.length; i++) {
			if(arrayReservation[i] == null) {
				continue;
			}

			String rezName = arrayReservation[i].getName();
			Room rezRoom = arrayReservation[i].getRoom();
			double amounts = rezRoom.getPrice();
			if (rezName.equals(name)) {
				amount = amount + amounts;
			}
		}
		System.out.println(name + "'s invoice is $" + amount);
	}

	//checks foe the type of the available rooms and adds them up
	public String toString() {
		int doubles = 0;
		int kings = 0;
		int queens = 0;
		for (int i =0; i <this.rooms.length; i++) {
			Room room = this.rooms[i];
			boolean availability = room.getAvailability();
			if (availability) {
				if (room.getType().equals("king")) {
					kings++;
				}
				if (room.getType().equals("double")) {
					doubles++;
				}
				if (room.getType().equals("queen")) {
					queens++;
				}
			}
		}
		if ((doubles ==0) && (queens ==0) && (kings == 0)) {
			return "There are no rooms available in this hotel at the moment";
		}
		return "Here is the hotel into: \n" + "Hotel name: " + this.name + "\nAvailable rooms: " + doubles + " double, " + queens + " queen, " + kings + " king. \n";
	}


}
