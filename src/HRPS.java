class HRPS
{
    /*------------------------------ Menu Items ------------------------------*/
    private static final String mainMenuMsg = "---------- Main Menu ----------\n1. Booking System\n2. Reservation System \n3. Room System\n4. Guest System\n5. Room Service System\n6. Order Service System\n7. Payment System\n0. Exit app\nInput : ";
    private static final String bookingMenuMsg = "---------- Booking System ----------\n1. Add booking\n2. Update booking\n3. Remove booking\n4. Display booking by room number\n0. Back to main menu\nInput : ";
    private static final String reservationMenuMsg = "---------- Reservation System ----------\n1. Add reservation\n2. Update reservation\n3. Remove reservation\n4. Display all reservation\n5. Display Reservation By Code\n6. Check for expiry\n0. Back to main menu\nInput : ";
    private static final String roomMenuMsg = "---------- Room System ----------\n1. Display all rooms\n2. Display empty rooms\n3. Display room by status\n4. Display room by room number\n5. Update room\n6. Update room price\n7. Add room type\n8. Remove room type\n0. Back to main menu\nInput : ";
    private static final String guestMenuMsg = "----------Guest Management System ----------\n1. Add guest\n2. Update guest\n3. Remove guest\n4. Show all guests\n5. Search by ID\n6. Search by Name\n0. Back to main menu\nInput : ";
    private static final String roomServiceMenuMsg = "---------- Room Service System ----------\n1. Add room service\n2. Update room service\n3. Remove room service\n4. Show all room services\n0. Back to main menu\nInput : ";
    private static final String orderRoomServiceMenuMsg = "---------- Order Service System ----------\n1. Order room service\n2. Update order\n3. Show all order\n0. Back to main menu\nInput : ";
    private static final String paymentMenuMsg = "---------- PaymentManager System ----------\n1. Pay by credit card\n2. Pay by cash\n0. Back to main menu\nInput : ";
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

    public static void main(String[] args)
    {
        // Initialize system managers
        billManager = new BillManager();
        guestManager = new GuestManager();
        roomManager = new RoomManager();
        roomServiceManager = new RoomServiceManager();
        orderManager = new OrderManager(billManager, roomServiceManager);
        reservationManager = new ReservationManager(roomManager, guestManager);
//        bookingManager = new BookingManager(guestManager, reservationManager, roomManager);
        paymentManager = new PaymentManager(billManager, orderManager);
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
                case 7:
                    paymentMenu();
                    break;
                case 0:
                    exitApp();
                    break;
                default:
                    displayOutput(invalidChoiceMsg);
            }
        } while (choice != 0);


        //reservationManager.displayAllReservation();
        //reservationManager.addReservation();
//    	//reservationManager.makeReservationExpired();
        //reservationManager.displayAllReservation();
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
            displayOutput(bookingMenuMsg);
            choice = Input.GetInt();
            int roomNum;

            switch (choice)
            {
                case 1:
                    bookingManager.add_booking();
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    roomNum = Input.GetInt();
                    System.out.print("Enter Customer Identity: ");
                    String cus_id = Input.GetString();
                    bookingManager.update_booking(roomNum, cus_id);
                    break;
                case 3:
                    bookingManager.Remove_booking();
                    break;
                case 4:
                    System.out.print("Enter room number: ");
                    roomNum = Input.GetInt();
                    bookingManager.displaybookingByRoomNum(roomNum);
                    break;
                // 	case 5 : ();
                // 		break;
                case 0:
                    mainMenu();
                    break;
                default:
                    displayOutput(invalidChoiceMsg);
            }
        } while (choice != 0);
    }

    private static void reservationMenu()
    {
        do
        {
            displayOutput(reservationMenuMsg);
            choice = Input.GetInt();

            switch (choice)
            {
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
                    reservationManager.makeReservationExpired();
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

    private static void roomMenu()
    {
        do
        {
            displayOutput(roomMenuMsg);
            choice = Input.GetInt();

            switch (choice)
            {
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

    private static void guestMenu()
    {
        do
        {
            displayOutput(guestMenuMsg);
            choice = Input.GetInt();

            switch (choice)
            {
                case 1:
                    guestManager.addGuest();
                    break;
                case 2:
                    guestManager.updateGuest();
                    break;
                //case 3 : guestManager.removeGuest();
                //break;
                case 4:
                    guestManager.displayAllGuest();
                    break;
                case 5:
                    displayOutput("Enter guest's ID : ");
                    String id = Input.GetString();
                    guestManager.searchGuestbyId(id);
                    break;
                case 6:
                    displayOutput("Enter guest's name : ");
                    String name = Input.GetString();
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

    private static void roomServiceMenu()
    {
        do
        {
            displayOutput(roomServiceMenuMsg);
            choice = Input.GetInt();

            switch (choice)
            {
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

    private static void orderRoomServiceMenu()
    {
        do
        {
            displayOutput(orderRoomServiceMenuMsg);
            choice = Input.GetInt();

            switch (choice)
            {
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

    private static void paymentMenu()
    {
        int choice;
        int roomNum;

        do
        {
            displayOutput(paymentMenuMsg);
            choice = Input.GetInt();

            switch (choice)
            {
                case 1:
                    System.out.println("Enter room number : ");
                    roomNum = Input.GetInt();
                    paymentManager.paybyCreditCard(roomNum);
                    break;
//                case 2:
//                    System.out.println("Enter room number : ");
//                    roomNum = Input.GetInt();
//                    paymentManager.paybyCash(roomNum);
//                    break;
                case 0:
                    mainMenu();
                    break;
                default:
                    displayOutput(invalidChoiceMsg);
            }
        } while (choice != 0);
    }

    private static void exitApp()
    {
        // Save data to file
        guestManager.WritetoFile();
        reservationManager.WritetoFile();
        roomManager.WritetoFile();
        roomServiceManager.WritetoFile();
        orderManager.WritetoFile();
        billManager.WritetoFile();

        displayOutput("Program is exiting...\n");

        // Delay the app from shutting down for feedback purposes
        try
        {
            Thread.sleep(500);
        } catch (InterruptedException ex)
        {
            // Do nothing as there will be no error
        }

        System.exit(0);
    }

    private static void displayOutput(String s)
    {
        System.out.print(s);
    }
}