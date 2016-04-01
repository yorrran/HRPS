import java.util.ArrayList;

class HRPS
{
    /*------------------------------ Menu Items ------------------------------*/
    private static final String mainMenuMsg = "---------- Main Menu ----------\n1. Booking System\n2. Room System\n3. Guest System\n4. Room Service System\n5. Payment System\n0. Exit app\nInput : ";
    private static final String bookingSystemMenuMsg = "---------- Booking System ----------\n0. Back to main menu\nInput : ";
	private static final String roomManagerMenuMsg = "---------- Room System ----------\n1. Display all rooms\n2. Display empty rooms\n3. Display room by status\n4. Display Room\n5. Update room\n6. Update room price\n7. Add room type\n8. Remove room type\n0. Back to main menu\nInput : ";
    private static final String guestManagerMenuMsg = "----------Guest Management System ----------\n1. Add guest\n2. Update guest\n3. Remove guest\n4. Search by ID\n5. Search by Name\n6. Show all guests\n0. Back to main menu\nInput : ";
    private static final String roomServiceMenuMsg = "---------- Room Service System ----------\n1. Add room service\n2. Update room service\n3. Remove room service\n4. Show all room services\n5. Order room service\n0. Back to main menu\nInput : ";
    private static final String paymentMenuMsg = "---------- Payment System ----------\n0. Back to main menu\nInput : ";
    private static final String invalidChoiceMsg = "Invalid option! Enter again!\n";
    private static int choice;
    
    /*------------------------------ Create system manager ------------------------------*/
//    private static BookingSystem bookingSystem;
    private static GuestDao guestDao;
    private static Booking booking;
    private static BookingManager bookingManager;
    
    private static RoomDao roomDao;
    private static RoomManager roomManager;
    private static ReservationDao reservationDao;
    private static ReservationManager reservationManager;
    private static RoomServiceManager roomServiceManager;
    private static GuestManager guestManager;
    
    private static ArrayList<Guest> guestList = null;
    private static ArrayList<Room> roomList = null;
	private static ArrayList<RoomType> roomTypeList = null;
	private static ArrayList<RoomFacing> roomFacingList = null;
	private static ArrayList<Reservation> reservationList = null;
	private static ArrayList<Booking> bookingList  = null;
//    private static PaymentManager paymentManager;

    public static void main(String[] args)
	{
        // Initialize system managers
//        bookingSystem = new BookingSystem();
    	guestDao=new GuestDaoImpl();
    	bookingManager=new BookingManager(bookingList ,guestList,guestManager,roomList,reservationList,reservationManager);
    	roomDao = new RoomDaoImpl();
    	reservationDao = new ReservationDaoImpl();
    	roomList = roomDao.getAllRoom();
		roomTypeList = roomDao.getAllRoomType();
		roomFacingList = roomDao.getAllRoomFacing();
    	roomManager = new RoomManager(roomDao,roomList);
    	reservationList = reservationDao.getAllReservation();
    	reservationManager = new ReservationManager(reservationDao, roomDao, reservationList, roomList);
    	
    	
        roomServiceManager = new RoomServiceManager();
        guestManager = new GuestManager();
        
//        paymentManager = new PaymentManager();

        mainMenu();

        //Email("xxxxx@gmail.com", "HRPS OODP", "Testing 1, 2, 3");
	}

    private static void mainMenu()
    {
        do
        {
            displayOutput(mainMenuMsg);
            choice = Input.GetInt();

            switch (choice)
            {
                case 1 : bookingMenu();
                    break;
                case 2 : roomMenu();
                    break;
                case 3 : guestMenu();
                	break;
                case 4 : roomServiceMenu();
                    break;
                case 5 : paymentMenu();
                    break;
                case 0 : exitApp();
                    break;
                default : displayOutput(invalidChoiceMsg);
            }
        } while (choice != 0);
    
    	
//    	roomManager.displayVacantRoom();
//    	
//    	reservationManager.displayAllReservation();
//    	reservationManager.addReservation();
//    	//reservationManager.makeReservationExpired();
//    	reservationManager.displayAllReservation();
//    	//reservationManager.updateReservation();
//    	
//    	//reservationManager.displayAllReservation();
//    	roomManager.displayVacantRoom();
//    	//reservationManager.removeReservation();
//    	exitApp();

    	
    }

	private static void bookingMenu()
	{
		do
		{
            clearScreen();
			displayOutput(bookingSystemMenuMsg);

            choice = Input.GetInt();

			switch (choice)
			{
			// 	case 1 : ();
			// 		break;
			// 	case 2 : ();
			// 		break;
			// 	case 3 : ();
			// 		break;
			// 	case 4 : ();
			// 		break;
			// 	case 5 : ();
			// 		break;
			    case 0 : mainMenu();
					break;
			 	default : displayOutput(invalidChoiceMsg);
			}
		} while (choice != 0);
	}
	
	private static void roomMenu()
	{
		do
		{
            clearScreen();
			displayOutput(roomManagerMenuMsg);
            choice = Input.GetInt();

			switch (choice)
			{
				case 1 : roomManager.displayAllRoom();
					break;
				case 2 : roomManager.displayVacantRoom();
					break;
				case 3 : roomManager.displayStatusByRoom();
					break;
				case 4 :
					System.out.print("Enter room number to view room: ");
					int roomNum = Input.GetInt();
					roomManager.displayRoom(roomNum);
					break;
				case 5 : roomManager.updateRoom();
					break;
				case 6 : roomManager.updateRoomPrice();
					break;
				case 7 : roomManager.addRoomType();
					break;
				case 8 : roomManager.removeRoomType();
					break;
				case 0 : mainMenu();
					break;
				default : displayOutput(invalidChoiceMsg);
					break;
			}
		} while (choice != 0);
	}

	private static void guestMenu()
	{
		do
		{
            clearScreen();
			displayOutput(guestManagerMenuMsg);

            choice = Input.GetInt();

			switch (choice)
			{
				case 1 : guestManager.addGuest();
					break;
				case 2 : guestManager.updateGuest();
					break;
				//case 3 : guestManager.removeGuest();
					//break;
				case 4 :
                    displayOutput("Enter guest's ID : ");
                    String id = Input.GetString();
                    guestManager.searchGuestbyId(id);
					break;
				case 5 :
                    displayOutput("Enter guest's name : ");
                    String name = Input.GetString();
                    guestManager.searchGuestbyName(name);
					break;
				case 6 : guestManager.displayAllGuest();
					break;
				case 0 : mainMenu();
					break;
				default : displayOutput(invalidChoiceMsg);
			}
		} while (choice != 0);
	}

	private static void roomServiceMenu()
	{
		do
		{
            clearScreen();
			displayOutput(roomServiceMenuMsg);

            choice = Input.GetInt();

			switch (choice)
			{
				case 1 : roomServiceManager.addRoomService();
					break;
				case 2 : roomServiceManager.updateRoomService();
					break;
				case 3 : roomServiceManager.removeRoomService();
					break;
				case 4 : roomServiceManager.displayRoomService();
					break;
//				case 5 : roomServiceManager.orderRoomService();
//					break;
				case 0 : mainMenu();
					break;
				default : displayOutput(invalidChoiceMsg);
			}
		} while (choice != 0);
	}

	private static void paymentMenu()
	{
		do
		{
			displayOutput(paymentMenuMsg);

            choice = Input.GetInt();

			switch (choice)
			{
//				case 1 : ();
//					break;
//				case 2 : ();
//					break;
//				case 3 : ();
//					break;
//				case 4 : ();
//					break;
//				case 5 : ();
//					break;
				case 0 : mainMenu();
					break;
				default : displayOutput(invalidChoiceMsg);
			}
		} while (choice != 0);
	}

    private static void exitApp()
    {
        clearScreen();
        
        // Save to file
        roomManager.WritetoFile();
        roomServiceManager.WritetoFile();
        guestManager.WritetoFile();
        reservationManager.WritetoFile();
        displayOutput("Program is exiting...\n");

        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException ex)
        {
            // Do nothing as there will be no error
        }

        System.exit(0);
    }

	private static void displayOutput(String s)
	{
		System.out.print(s);
	}

    private static void clearScreen()
    {
        // Not working on windows
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
    }
}