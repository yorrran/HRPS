import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class BookingManager
{
    private BookingDao bookingDao;
    private GuestManager guestManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private BillManager billManager;
    private OrderManager orderManager;
    private PaymentManager paymentManager;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private ArrayList<Room> availableRoomList = null;
    
    public BookingManager(GuestManager guestManager,ReservationManager reservationManager,RoomManager roomManager, BillManager billManager, OrderManager orderManager, PaymentManager paymentManager)
    {
        bookingDao = new BookingDaoImpl();
        this.guestManager = guestManager;
        this.roomManager=roomManager;
        this.reservationManager = reservationManager;
       this.billManager = billManager;
       this.orderManager = orderManager;
       this.paymentManager = paymentManager;
    }

    public void add_booking()
    {
        System.out.println("Is the customer walk-in or reservation?");
        System.out.println("Press 1 for walk-in customer");
        System.out.println("Press 2 for reservation customer");
        boolean flag = false;
        int cus_type = Input.GetInt();
        int i;
      
        if (cus_type == 1)
        {
        	 System.out.println("Please enter customer identity");
             String cus_id = Input.GetString();
            for (i = 0; i < guestManager.getGuestDao().getAllGuest().size(); i++)
            {
                if (guestManager.getGuestDao().getAllGuest().get(i).getIdentity().equals(cus_id))
                {
                	System.out.println("Customer already exists");
                 	flag=true;
                	break;
                }
                    
            }
            if (!flag)
            {
            	System.out.println("This customer is a new customer");
                guestManager.addGuest();
            }
            walkin_cus(cus_id);
        }
        else if(cus_type == 2)
        {
        	System.out.println("Please key in reservation code");
        	String reservation_code=Input.GetString();
            reserve_cus(reservation_code);
        }
    }

    private void walkin_cus(String cus_id)
    {
    	Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date checkInDate = digiClock.getTime();//get reservation datetime
        Date tempDate = digiClock.getTime();
        
        Room room = null;
        System.out.print("Enter number of adults: ");
        int numberOfAdults = Input.GetInt();

        System.out.print("Enter number of children: ");
        int numberOfChildren = Input.GetInt();
        

        Date checkOutDate = null;
        do
        {
        
        System.out.print("Enter check out date(dd/MM/yyyy): ");
        String checkOutString = Input.GetString();
        
        try
        {
            checkOutDate = dateFormat.parse(checkOutString);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }//get check out date
        if(checkInDate.compareTo(checkOutDate)>=0)
        	System.out.println("Check in date must be eaelier than check out date");
        }while(checkInDate.compareTo(checkOutDate)>=0);
        
      
        
        long diff = checkOutDate.getTime() - checkInDate.getTime();
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));//get difference of days between check out and check in
        availableRoomList = new ArrayList<Room>(roomManager.getAllRoom());//make a copy of room list
        reservationManager.updateAvailableRoomList(availableRoomList, tempDate, checkOutDate);
        reservationManager.displayAvailableRoom(availableRoomList);
        
        System.out.println("Continue to book a room?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        
        int choice  = Input.GetInt();
        while (choice < 1 || choice > 2)
        {
            System.out.print("Please enter correct choice: ");
            choice = Input.GetInt();
        }  System.out.println("");
        
        switch(choice)
        {
        	case 1:
        		 do
        	        {
        			 	reservationManager.displayAvailableRoom(availableRoomList);
        	            System.out.print("Enter the room number: ");
        	            int roomNum = Input.GetInt(); 
        	            boolean foundAvailableRoom =false;
        	            room = roomManager.getRoomByRoomNum(roomNum);
        	            	 for(int i=0;i<availableRoomList.size();i++)
        	                 {
        	                 	if(room.roomNumber == availableRoomList.get(i).getRoomNumber())
        	                 	{
        	                 		foundAvailableRoom = true;
        	                 		break;
        	                 	}
        	                 }   
        	                 if(foundAvailableRoom)
        	                 {
        	     	            if (room.getRoomStatus().equals(RoomStatus.returnStatus(4)))//not allow to book if room is under maintain
        	     	            {
        	     	                System.out.println("This room is unavailable now, please select another room.");
        	     	                room = null;
        	     	            }
        	                  }
        	                  else
        	                 {	
        	                 	System.out.println("The room is unavailable in that period, please select another room.");
        	                 	room = null;
        	                 }          
        	        } while (room == null);//selecte the vacant room
        		 
        		 double price = (room.getRoomType().getPrice() + room.getFacing().getPrice());//get price for room type and facing type
        		 System.out.println(dateFormat.format(checkInDate));
     	        Booking booking = new Booking(cus_id, room, price, checkInDate, checkOutDate, numberOfAdults, numberOfChildren);
     	        billManager.createBill(room.roomNumber);
     	       orderManager.createOrder(room.roomNumber);
     	        bookingDao.addbooking(booking);
     	       roomManager.getRoomByRoomNum(room.roomNumber).setRoomStatus(RoomStatus.returnStatus(2));
        	case 2:
            break;
        }
    }

    private void reserve_cus(String reservation_code)
    {
    	Reservation reservation = reservationManager.searchReservationbyReservationCode(reservation_code);
    	Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date current = digiClock.getTime();//get booking datetime 
        digiClock.set(Calendar.HOUR_OF_DAY,19);
        digiClock.set(Calendar.MINUTE,52);
        digiClock.set(Calendar.SECOND,0);
         
        Date tempCheckInDate = new Date();
        String strCheckIn = null;
        strCheckIn =  dateFormat.format(reservation.getCheckInDatetime());
		try {
			tempCheckInDate = dateFormat.parse(strCheckIn);
			tempCheckInDate.setHours(digiClock.getTime().getHours());
			tempCheckInDate.setMinutes(digiClock.getTime().getMinutes());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
    	
    	if(current.before(tempCheckInDate))
    	{
    		System.out.println("Customer can not check in yet");
    	}
    	else if(reservation.getReservationStatus().equals(ReservationStatus.returnStatus(4)))
    	{
    		System.out.println("The reservation is expired");
    	}
    	else
    	{
    		if(reservation.getReservationStatus().equals(ReservationStatus.returnStatus(3)))
    		{
    			System.out.println("Customer has already checked in");
    		}
    		else
    		{
    		 Booking booking = new Booking(reservation.getIdentity(), roomManager.getRoomByRoomNum(reservation.getRoom().roomNumber),
             		reservation.getPrice(), reservation.getCheckInDatetime(),
             		reservation.getCheckOutDatetime(), reservation.getNumOfAdult(),
             		reservation.getNumOfAdult());
             roomManager.getRoomByRoomNum(reservation.getRoom().roomNumber).setRoomStatus(RoomStatus.returnStatus(2));
             reservation.setReservationStatus(ReservationStatus.returnStatus(3));
             billManager.createBill(reservation.getRoom().roomNumber);
   	       orderManager.createOrder(reservation.getRoom().roomNumber);
             bookingDao.addbooking(booking);
    		}
    	}
               

    }
    

    public void update_booking( int roomNum, String cus_id)
    {
        Calendar digiClock = Calendar.getInstance();
        Calendar temp = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date bookingDatetime = digiClock.getTime();//get booking datetime
        Date current=temp.getTime();
        boolean status=true;
        
        System.out.print("roomNum"+roomNum + "to update reservation: ");
        Booking booking = bookingDao.searchBookingByRoomNum(roomNum);
        if (current.after(booking.getCheckOutDatetime()))
        {
            System.out.println("Booking is expired, update is not allow");
        }
        
        else
        {
        System.out.println("Booking Information ");
        System.out.println("1. Stay Extension");
        System.out.println("2. Number of adults");
        System.out.println("3. Number of children");
        System.out.println("4. Cancel update");
        System.out.print("Select which detail you want to update: ");
        int choice = Input.GetInt();
        while (choice < 1 || choice > 6)
        {
            System.out.print("Please enter correct choice: ");
            choice = Input.GetInt();
        }
        System.out.println("");
        
        switch(choice)
        {
        case 1:
        	for (int i = 0; i < bookingDao.getAllBooking().size(); i++)
            {
                if (bookingDao.getAllBooking().get(i).getRoom().roomNumber==roomNum)
                {
               
                if (bookingDao.getAllBooking().get(i).getIdentity().equals(cus_id))
                {
                		
                    System.out.println("How many days that need to be extended");
                    int extendDays = Input.GetInt();
                    Date checkInDate=reservationManager.getReservationDao().getAllReservation().get(i).getCheckInDatetime();
                    Date checkOutDate=reservationManager.getReservationDao().getAllReservation().get(i).getCheckOutDatetime();
                    int diff = (int)(checkOutDate.getTime() - checkInDate.getTime());
                    int totalDays=diff+extendDays;
                    
                    digiClock.add(Calendar.DATE,totalDays);
                    Date newCheckOutDays=digiClock.getTime();
                    
                  
                     temp.add(Calendar.DATE, diff);
                     Date startExtention = temp.getTime();
                     for (int j = 0; j <reservationManager.getReservationDao().getAllReservation().size(); j++)
                     {
                    	  if( reservationManager.getReservationDao().getAllReservation().get(j).getRoom().roomNumber==roomNum&&!reservationManager.getReservationDao().getAllReservation().get(j).getIdentity().equals(cus_id))
                    	  {
                    		  if(newCheckOutDays.after(reservationManager.getReservationDao().getAllReservation().get(j).getCheckInDatetime())&&newCheckOutDays.before(reservationManager.getReservationDao().getAllReservation().get(j).getCheckOutDatetime()))
                    		  {
                    			  status=false;
                    		  }
                    		  else
                    		  {
                    			  bookingDao.getAllBooking().get(i).setCheckOutDatetime(newCheckOutDays); 
                    		  }
                    	  }
                    		  
                    	  
                    if (!status)
                    {
                        System.out.println("The room is reserved by other customers");
                        System.out.println("Would you like to book another room");
                        System.out.println("1.book another room");
                        System.out.println("2.cancle booking");
                        int option = Input.GetInt();
                        if (option == 1)
                        {
                            walkin_cus(cus_id);
                        }
                        else
                        System.out.println("The updation is been cancelled");

                    }
                    else
                    {
                    	bookingDao.getAllBooking().get(i).setCheckOutDatetime(newCheckOutDays);
                    }
                }
                }
              }
            }
            
        break;
                     
        case 2:
            System.out.print("Enter number of adults: ");
            int numberOfAdults = Input.GetInt();
            booking.setNumOfAdult(numberOfAdults);
            break;
         case 3:
            System.out.print("Enter number of children: ");
            int numberOfChildren = Input.GetInt();
            booking.setNumOfAdult(numberOfChildren);
            break;
         default:
            System.out.println("Update canceled.");
         }
        }
    }
    public void displaybookingByRoomNum(int roomNum)
    {
        Booking booking = bookingDao.searchBookingByRoomNum(roomNum);
        String identity = booking.getIdentity();
        Room room = booking.getRoom();
        double price = booking.getPrice();
        Date checkInDatetime =  booking.getCheckInDatetime();
        Date checkOutDatetime =  booking.getCheckOutDatetime();
        int numOfAdult =  booking.getNumOfAdult();
        int numOfChildren =booking.getNumOfChildren();

        System.out.println("Customer Identity: " + identity );
        System.out.println("Room number: " + room.getRoomNumber() + ", Price: " + price + ", Check In Date: " + dateFormat.format(checkInDatetime) + ", Check Out Date: " + dateFormat.format(checkOutDatetime));
        System.out.println("Number of adult: " + numOfAdult + ", number of children: " + numOfChildren);
        System.out.println("---------------------------------------");
    }
    public void checkOut()
    {
    	Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date current = digiClock.getTime();//get booking datetime
        int roomNum;
        long diff;
    	int numOfDays;
    	double price;
    	 Booking booking;
    	 System.out.println("Please Enter room number: " );
    	 roomNum=Input.GetInt();
    	 
    	 System.out.println("Which type of checkOut it is?" );
    	 System.out.println("1.Early Checkout" );
    	 System.out.println("2.Normal Checkout" );
    	 int choice=Input.GetInt();
    	 switch(choice)
    	 {
    	 case 1:
   
    	
    	    	booking = bookingDao.searchBookingByRoomNum(roomNum);
    	    	roomManager.getRoomByRoomNum(roomNum).setRoomStatus(RoomStatus.returnStatus(1));
    	                	diff=current.getTime()-booking.getCheckInDatetime().getTime();
    	                	 numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
    	                	price=booking.getPrice()*numOfDays;
    	                	billManager.getBillDao().updateRoomCharge(roomNum, price);
    	                	bookingDao.removeBooking(booking);
    	                	System.out.println("Customer has alreday checked out" );
    	                	paymentManager.pay(roomNum);
    	            
    	            
    	  break;
    	 case 2:
    		 
	    	 booking = bookingDao.searchBookingByRoomNum(roomNum);
	    	 roomManager.getRoomByRoomNum(roomNum).setRoomStatus(RoomStatus.returnStatus(1));
	                	 diff=booking.getCheckOutDatetime().getTime()-booking.getCheckInDatetime().getTime();
	                	 numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
	                	price=booking.getPrice()*numOfDays;
	                	billManager.getBillDao().updateRoomCharge(roomNum, price);
	                	bookingDao.removeBooking(booking);
	                	paymentManager.pay(roomNum);
	            
	           
	     break;
    	                
      }
    }
    	    	 
    public void autoCheckOut()
    {	    	 
        
    	Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        digiClock.set(Calendar.HOUR_OF_DAY,12);
        digiClock.set(Calendar.MINUTE,0);
        digiClock.set(Calendar.SECOND,0);
        Date current = digiClock.getTime();//get booking datetime  
        
        for (int i = 0; i < bookingDao.getAllBooking().size(); i++)
        {
            
    	  if (bookingDao.getAllBooking().get(i).getCheckOutDatetime().after(current))
    	  {
    		 System.out.print("The bookig is expired");
    		 roomManager.getRoomByRoomNum(bookingDao.getAllBooking().get(i).getRoom().getRoomNumber()).setRoomStatus(RoomStatus.returnStatus(1));
        	 long diff=bookingDao.getAllBooking().get(i).getCheckOutDatetime().getTime()-bookingDao.getAllBooking().get(i).getCheckInDatetime().getTime();
        	 int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        	double price=bookingDao.getAllBooking().get(i).getPrice()*numOfDays;
    	     bookingDao.removeBooking(bookingDao.getAllBooking().get(i));//only allow to remove when the status is expired
    	  }
    	  else
    	  {
    	       System.out.println("This booking cannot be removed in this moment");
    	  }
        }
    }
    public void displayBooking()
    {
        bookingDao.displayBooking();
    }
    
    public void WritetoFile()
    {
    	bookingDao.updateFile();
    }


}
