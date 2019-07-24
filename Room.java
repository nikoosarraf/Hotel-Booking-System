package A4;
//Nikoo Sarraf
//260767310

public class Room {

	private String type;
	private double price;
	private boolean availability;

	//assigns a price to each room type
	//also throws an exception if the user tries to put a room type that doesn't exist
	public Room(String roomType) {
		this.type = roomType;
		if (roomType.equals("double")) {
			price = 90.0;
		}
		else if (roomType.equals("queen")) {
			price = 110.0;
		}
		else if(roomType.equals("king")) {
			price = 150.0;
		}
		else {
			throw new IllegalArgumentException("Please input one of the following room types: double, queen, or king");
		}
		availability = true;
	}

	//three getter methods to return type, price and availability of a room
	public String getType() {
		return this.type;
	}

	public double getPrice() {
		return this.price;
	}

	public boolean getAvailability() {
		return availability;
	}

	//changes the availability after a reservation has been made or removed
	public void changeAvailability() {
		availability = !availability;
	}

	//searches through an array of rooms to find an empty one that matches the desired type and returns that room
	public static Room findAvailableRoom(Room[] room1, String roomType) {
		for (int i =0; i< room1.length; i++) {
			if(room1[i].getType().equals(roomType) && room1[i].getAvailability()) {
				return room1[i];
			}
		}
		return null;
	}
}
