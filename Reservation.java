package A4;
//Nikoo Sarraf
//260767310

public class Reservation {
	private String name;
	private Room roomReserved;
	
	public Reservation(String name, Room roomR) {
		this.name = name;
		this.roomReserved = roomR;
	}
	
	//getter methods to return the name and type of the room reserved
	public String getName() {
		return this.name;
	}
	
	public Room getRoom() {
		return this.roomReserved;
	}
}




