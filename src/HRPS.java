class HRPS
{
    /*------------------------------ Menu Items ------------------------------*/
    private static final String mainMenuMsg = "---------- Main Menu ----------\n1. Booking System\n2. Room System\n3. Guest System\n4. Room Service System\n5. Order Service System\n6. PaymentManager System\n0. Exit app\nInput : ";
    private static final String bookingSystemMenuMsg = "---------- Booking System ----------\n1. Add booking\\n2. Update booking\\n3. Remove booking\\n4. Display booking by room number\n0. Back to main menu\nInput : ";
    private static final String roomManagerMenuMsg = "---------- Room System ----------\n1. Display all rooms\n2. Display empty rooms\n3. Display room by status\n4. Display Room\n5. Update room\n6. Update room price\n7. Add room type\n8. Remove room type\n0. Back to main menu\nInput : ";
    private static final String guestManagerMenuMsg = "----------Guest Management System ----------\n1. Add guest\n2. Update guest\n3. Remove guest\n4. Search by ID\n5. Search by Name\n6. Show all guests\n0. Back to main menu\nInput : ";
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

        reservationManager = new ReservationManager(roomManager);
//        bookingManager = new BookingManager(guestManager, reservationManager, roomManager);
        orderManager = new OrderManager(billManager, roomManager, roomServiceManager);
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
                case 1: bookingMenu();
                    break;
                case 2: roomMenu();
                    break;
                case 3: guestMenu();
                    break;
                case 4: roomServiceMenu();
                    break;
                case 5: orderRoomServiceMenu();
                    break;
                case 6: paymentMenu();
                    break;
                case 0: exitApp();
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
            displayOutput(bookingSystemMenuMsg);
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

    private static void roomMenu()
    {
        do
        {
            displayOutput(roomManagerMenuMsg);
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
            displayOutput(guestManagerMenuMsg);
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
                    displayOutput("Enter guest's ID : ");
                    String id = Input.GetString();
                    guestManager.searchGuestbyId(id);
                    break;
                case 5:
                    displayOutput("Enter guest's name : ");
                    String name = Input.GetString();
                    guestManager.searchGuestbyName(name);
                    break;
                case 6:
                    guestManager.displayAllGuest();
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
                case 1 : roomServiceManager.addRoomService();
                    break;
                case 2 : roomServiceManager.updateRoomService();
                    break;
                case 3 : roomServiceManager.removeRoomService();
                    break;
                case 4 : roomServiceManager.displayRoomService();
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
				case 1 : orderManager.addOrder();
					break;
				case 2 : orderManager.updateOrder();
					break;
				case 3 : orderManager.displayAllOrder();
					break;
                case 4: orderManager.displayOrderbyRoomNum();
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