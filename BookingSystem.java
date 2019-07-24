package A4;
//Nikoo Sarraf
//260767310

import java.util.Scanner;
import java.util.Random;

public class BookingSystem {

		//given code
	    private static String[] typeOfRooms = {"double","queen","king"};
	    private static Random r = new Random(123);

	    //returns a random String from the above array.
	    private static String getRandomType(){
	        int index = r.nextInt(typeOfRooms.length);
	        return typeOfRooms[index];
	    }
	    //returns a random number of rooms between 5 and 50.
	    private static int getRandomNumberOfRooms(){
	        return r.nextInt(50)+1;
	    }
	    //end of given code

	    public int t = getRandomNumberOfRooms();

    	//helper method menu() prints out the menu of options and performs the commands
    //scanner at the end asks the user to enter an integer corresponding to the menu options
	private static boolean menu (Hotel newHotel) {
		System.out.println("*****************************************************");
		System.out.println("1. Make a reservation");
		System.out.println("2. Cancel a reservation");
		System.out.println("3. See an invoice");
		System.out.println("4. See the hotel info");
		System.out.println("5. Exit the booking system");
		Scanner askNumber = new Scanner(System.in);
		System.out.println("*****************************************************");
		int Number = askNumber.nextInt();

		//series of if statements for each option on the menu
		//option 1: create a reservation, using scanner to get the guest name and desired room type
		if (Number == 1) {
			Scanner askName = new Scanner(System.in);
			System.out.println("Please enter your name");
			String guestName = askName.nextLine();
			Scanner askType = new Scanner(System.in);
			System.out.println("What type of room would you like to reserve?");
			String roomType = askType.nextLine();
			newHotel.createReservation(guestName, roomType);
			return true;
		}

		//option 2: removes a reservation, using scanner to get the guest name and desired room type
		if (Number == 2) {
			Scanner askName = new Scanner(System.in);
			try {
				System.out.println("Please enter your name: ");
				String name = askName.nextLine();
				Scanner askRoom = new Scanner(System.in);
				System.out.println("What type of room did you reserve?");
				String room = askRoom.nextLine();
				newHotel.cancelReservation(name, new Room(room));
				return true;
			}
			catch (IllegalArgumentException e) {
				System.out.println("Invalid room type entered. Try again: ");
				System.out.println("Please enter your name: ");
				String name = askName.nextLine();
				Scanner askRoom = new Scanner(System.in);
				System.out.println("What type of room did you reserve?");
				String room = askRoom.nextLine();
				newHotel.cancelReservation(name, new Room(room));
				return true;
			}
		}

		//option 3: uses the printInvoice method
		if (Number == 3) {
			Scanner askName = new Scanner(System.in);
			System.out.println("Please enter your name");
			String name = askName.nextLine();
			newHotel.printInvoice(name);
			return true;
		}

		//option 4: prints hotel info
		if (Number == 4) {
			System.out.print(newHotel.toString());
			return true;
		}

		//option 5: prints a line, and does not continue the program
		//only option which returns false, meaning the program will not continue
		if (Number == 5) {
			System.out.println("It was a pleasure doing business with you!");
			return false;
		}

		//if the integer is not 1-5, reprints the menu
		if (Number < 1 || Number > 5) {
			return false;
		}
		return false;
	}
	
	//main method
	//scanner takes in hotel and visitor name
	//initializes a new hotel and room array
	public static void main(String[] args) {
		System.out.println("Welcome to the booking system!");
		Scanner askHotel = new Scanner(System.in);
		System.out.println("Please enter the name of the hotel you'd like to book");
		String hotelName = askHotel.nextLine();
		Hotel newHotel = null;
		Room[] room = new Room[getRandomNumberOfRooms()];
		for (int i = 0; i < room.length; i++) {
			room[i] = new Room(getRandomType());
		}
		try{
			newHotel = new Hotel(hotelName, room);
			System.out.println("Welcome to " + hotelName + ". Choose one of the following options:");
			Scanner askNumber = new Scanner(System.in);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		while (menu(newHotel) == true) {
			menu(newHotel);
		}
	}
}
