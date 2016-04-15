import java.util.Scanner;

/**
 * Main interface of the hotel reservation system.
 */
public class HRPS {
	/*------------------------------ Menu Items ------------------------------*/
	private static final String mainMenuMsg = "---------- Main Menu ----------\n1. Booking System\n2. Reservation System \n3. Room System\n4. Guest System\n5. Room Service System\n6. Order Service System\n0. Exit app\nInput : ";
	private static final String bookingMenuMsg = "---------- Booking System ----------\n1. Check-in\n2. Update booking\n3. Check-out\n4. Display booking by room number\n0. Back to main menu\nInput : ";
	private static final String reservationMenuMsg = "---------- Reservation System ----------\n1. Add reservation\n2. Update reservation\n3. Remove reservation\n4. Display all reservation\n5. Display Reservation By Code\n6. Update wait-list\n7. Email guest as reminder\n0. Back to main menu\nInput : ";
	private static final String roomMenuMsg = "---------- Room System ----------\n1. Display all rooms\n2. Display empty rooms\n3. Display room by status\n4. Display room by room number\n5. Update room\n6. Update room price\n7. Add room type\n8. Remove room type\n0. Back to main menu\nInput : ";
	private static final String guestMenuMsg = "----------Guest Management System ----------\n1. Add guest\n2. Update guest\n3. Show all guests\n4. Search by ID\n5. Search by Name\n0. Back to main menu\nInput : ";
	private static final String roomServiceMenuMsg = "---------- Room Service System ----------\n1. Add room service\n2. Update room service\n3. Remove room service\n4. Show all room services\n0. Back to main menu\nInput : ";
	private static final String orderRoomServiceMenuMsg = "---------- Order Service System ----------\n1. Order room service\n2. Update order\n3. Show all order\n0. Back to main menu\nInput : ";
	private static final String invalidChoiceMsg = "Invalid option! Enter again!\n";
	private static int choice;

	/*------------------------------ Create system manager ------------------------------*/
	private static BillManager billManager;
	private static BookingManager bookingManager;
	private static GuestManager guestManager;
	private static RoomManager roomManager;
	private static ReservationManager reservationManager;
	private static RoomServiceManager roomServiceManager;
	private static OrderManager orderManager;
	private static PaymentManager paymentManager;

	/**
	 * Main method of the console application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize system managers
		billManager = new BillManager();
		guestManager = new GuestManager();
		roomManager = new RoomManager();
		roomServiceManager = new RoomServiceManager();
		orderManager = new OrderManager(billManager, roomServiceManager);
		reservationManager = new ReservationManager(roomManager, guestManager);
		paymentManager = new PaymentManager(billManager, orderManager);
		bookingManager = new BookingManager(guestManager, reservationManager,
				roomManager, billManager, orderManager, paymentManager);
		mainMenu();
	}

	/**
	 * Main menu interface.
	 */
	private static void mainMenu() {
		do {
			displayOutput(mainMenuMsg);
			choice = Input.GetInt();

			switch (choice) {
			case 1:
				bookingMenu();
				break;
			case 2:
				reservationMenu();
				break;
			case 3:
				roomMenu();
				break;
			case 4:
				guestMenu();
				break;
			case 5:
				roomServiceMenu();
				break;
			case 6:
				orderRoomServiceMenu();
				break;
			case 0:
				exitApp();
				break;
			default:
				displayOutput(invalidChoiceMsg);
			}
		} while (choice != 0);
	}

	/**
	 * Booking system interface. Calls the necessary methods from
	 * BookingManager.
	 */
	private static void bookingMenu() {
		do {
			displayOutput(bookingMenuMsg);
			choice = Input.GetInt();
			int roomNum;

			switch (choice) {
			case 1:
				bookingManager.addBooking();
				break;
			case 2:
				System.out.print("Enter room number : ");
				roomNum = Input.GetInt();
				System.out.print("Enter Customer Identity : ");
				String cus_id = Input.GetString();
				bookingManager.updateBooking(roomNum, cus_id);
				break;
			case 3:
				bookingManager.checkOut();
				break;
			case 4:
				System.out.print("Enter room number : ");
				roomNum = Input.GetInt();
				bookingManager.displaybookingByRoomNum(roomNum);
				break;
			case 5:
				break;
			case 0:
				mainMenu();
				break;
			default:
				displayOutput(invalidChoiceMsg);
			}
		} while (choice != 0);
	}

	/**
	 * Reservation system interface. Calls the necessary methods from
	 * ReservationManager.
	 */
	private static void reservationMenu() {
		do {
			displayOutput(reservationMenuMsg);
			choice = Input.GetInt();

			switch (choice) {
			case 1:
				reservationManager.addReservation();
				break;
			case 2:
				reservationManager.updateReservation();
				break;
			case 3:
				reservationManager.removeReservation();
				break;
			case 4:
				reservationManager.displayAllReservation();
				break;
			case 5:
				reservationManager.displayReservationByCode();
				break;
			case 6:
				reservationManager.updateWaitList();
				break;
			case 7:
				reservationManager.emailGuest();
				break;
			case 0:
				mainMenu();
				break;
			default:
				displayOutput(invalidChoiceMsg);
				break;
			}
		} while (choice != 0);
	}

	/**
	 * Room system interface. Calls the necessary methods from RoomManager.
	 */
	private static void roomMenu() {
		do {
			displayOutput(roomMenuMsg);
			choice = Input.GetInt();

			switch (choice) {
			case 1:
				roomManager.displayAllRoom();
				break;
			case 2:
				roomManager.displayVacantRoom();
				break;
			case 3:
				roomManager.displayStatusByRoom();
				break;
			case 4:
				System.out.print("Enter room number to view room: ");
				int roomNum = Input.GetInt();
				roomManager.displayRoom(roomNum);
				break;
			case 5:
				roomManager.updateRoom();
				break;
			case 6:
				roomManager.updateRoomPrice();
				break;
			case 7:
				roomManager.addRoomType();
				break;
			case 8:
				roomManager.removeRoomType();
				break;
			case 0:
				mainMenu();
				break;
			default:
				displayOutput(invalidChoiceMsg);
				break;
			}
		} while (choice != 0);
	}

	/**
	 * Guest system interface. Calls the necessary methods from GuestManager.
	 */
	private static void guestMenu() {
		do {
			displayOutput(guestMenuMsg);
			choice = Input.GetInt();

			switch (choice) {
			case 1:

				guestManager.addGuest();
				break;
			case 2:
				guestManager.updateGuest();
				break;
			case 3:
				guestManager.displayAllGuest();
				break;
			case 4:
				displayOutput("Enter guest's ID : ");
				String id = Input.GetString();
				guestManager.searchGuestbyId(id);
				break;
			case 5:
				displayOutput("Enter guest's name : ");
				Scanner sc = new Scanner(System.in);
				String name = sc.nextLine();
				guestManager.searchGuestbyName(name);
				break;
			case 0:
				mainMenu();
				break;
			default:
				displayOutput(invalidChoiceMsg);
			}
		} while (choice != 0);
	}

	/**
	 * Room service system interface. Calls the necessary methods from
	 * RoomServiceManager.
	 */
	private static void roomServiceMenu() {
		do {
			displayOutput(roomServiceMenuMsg);
			choice = Input.GetInt();

			switch (choice) {
			case 1:
				roomServiceManager.addRoomService();
				break;
			case 2:
				roomServiceManager.updateRoomService();
				break;
			case 3:
				roomServiceManager.removeRoomService();
				break;
			case 4:
				roomServiceManager.displayRoomService();
				break;
			case 0:
				mainMenu();
				break;
			default:
				displayOutput(invalidChoiceMsg);
			}
		} while (choice != 0);
	}

	/**
	 * Order room service system interface. Calls the necessary methods from
	 * OrderManager.
	 */
	private static void orderRoomServiceMenu() {
		do {
			displayOutput(orderRoomServiceMenuMsg);
			choice = Input.GetInt();

			switch (choice) {
			case 1:
				orderManager.addOrder();
				break;
			case 2:
				orderManager.updateOrder();
				break;
			case 3:
				orderManager.displayAllOrder();
				break;
			case 4:
				orderManager.displayOrderbyRoomNum();
				break;
			case 0:
				mainMenu();
				break;
			default:
				displayOutput(invalidChoiceMsg);
			}
		} while (choice != 0);
	}

	/**
	 * Write all data to the data file before exiting the console application.
	 */
	private static void exitApp() {
		// Save data to file
		guestManager.WritetoFile();
		reservationManager.WritetoFile();
		roomManager.WritetoFile();
		roomServiceManager.WritetoFile();
		orderManager.WritetoFile();
		billManager.WritetoFile();
		bookingManager.WritetoFile();
		displayOutput("Program is exiting...\n");

		// Delay the application from shutting down for illustration purposes
		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			// Do nothing as there will be no error
		}

		System.exit(0);
	}

	/**
	 * Show the system messages for each systems.
	 * 
	 * @param s
	 *            the message to be displayed
	 */
	private static void displayOutput(String s) {
		System.out.print(s);
	}
}