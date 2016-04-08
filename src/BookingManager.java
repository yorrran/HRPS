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
    private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
    private ArrayList<Room> availableRoomList = null;
    
    public BookingManager(GuestManager guestManager,ReservationManager reservationManager,RoomManager roomManager)
    {
        bookingDao = new BookingDaoImpl();
        this.guestManager = guestManager;
        this.roomManager=roomManager;
        this.reservationManager = reservationManager;
       
    }

    public void add_booking()
    {
        System.out.println("Is the customer walk-in or reservation?");
        System.out.println("Press 1 for walk-in customer");
        System.out.println("Press 2 for reservation customer");
        boolean flag = false;
        int cus_type = Input.GetInt();
        int i;
        System.out.println("Please enter customer identity");
        String cus_id = Input.GetString();
        if (cus_type == 1)
        {
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
        	System.out.println("This customer made reservation");
            reserve_cus(cus_id);
        }
    }

    private void walkin_cus(String cus_id)
    {
    	Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date current = digiClock.getTime();//get reservation datetime
    	
        System.out.print("Enter your passport or driving license: ");
        String identity = null;
        identity = Input.GetString();
        
        
        
        Room room = null;
        System.out.print("Enter number of adults: ");
        int numberOfAdults = Input.GetInt();

        System.out.print("Enter number of children: ");
        int numberOfChildren = Input.GetInt();
        
        Date checkInDate = current;
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
        
        availableRoomList = new ArrayList<Room>(roomManager.getAllRoom());//make a copy of room list
       // this.updateAvailableRoomList(availableRoomList, tempdate, checkOutDate);
        
        long diff = checkOutDate.getTime() - checkInDate.getTime();
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));//get difference of days between check out and check in
        availableRoomList = new ArrayList<Room>(roomManager.getAllRoom());//make a copy of room list
        reservationManager.updateAvailableRoomList(availableRoomList, checkInDate, checkOutDate);
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
        		 
        		 double price = numOfDays * (room.getRoomType().getPrice() + room.getFacing().getPrice());//get price for room type and facing type

     	        Booking booking = new Booking(cus_id, room, price, checkInDate, checkOutDate, numberOfAdults, numberOfChildren);
     	        bookingDao.addbooking(booking);
        	case 2:
            break;
        }
    }

    private void reserve_cus(String cus_id)
    {
        for (int i = 0; i < reservationManager.getReservationDao().getAllReservation().size(); i++)
        {
            if (reservationManager.getReservationDao().getAllReservation().get(i).getIdentity().equals(cus_id))
            {
                Booking booking = new Booking(cus_id, reservationManager.getReservationDao().getAllReservation().get(i).getRoom(),
                		reservationManager.getReservationDao().getAllReservation().get(i).getPrice(), reservationManager.getReservationDao().getAllReservation().get(i).getCheckInDatetime(),
                		reservationManager.getReservationDao().getAllReservation().get(i).getCheckOutDatetime(), reservationManager.getReservationDao().getAllReservation().get(i).getNumOfAdult(),
                		reservationManager.getReservationDao().getAllReservation().get(i).getNumOfAdult());
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
        System.out.println("2. Room number");
        System.out.println("3. Number of adults");
        System.out.println("4. Number of children");
        System.out.println("5. Cancel update");
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
                    		  //check days overlapping here
                    	  }
                    		  
                    	  
                    if (false)
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
        	reservationManager.displayAvailableRoom(availableRoomList);
        	System.out.print("Enter the room number: ");
            int newRoomNum = Input.GetInt();
            
        	Room room = null;
        	
           //find a room, days overlapping needs to be taken care
        	
             long diff = bookingDao.searchBookingByRoomNum(roomNum).getCheckOutDatetime().getTime() - current.getTime();
             int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));//get difference of days between check out and check in
             double price = numOfDays * (room.getRoomType().getPrice() + room.getFacing().getPrice());//get price for room type and facing type
             booking.setCheckInDatetime(current);
             booking.setRoom(room);
             booking.setPrice(price);
             booking.setPrice(bookingDao.searchBookingByRoomNum(roomNum).getCheckOutDatetime().getTime());
             
             for (int i = 0; i < bookingDao.getAllBooking().size(); i++)
             {
                 if (roomManager.getAllRoom().get(i).getRoomNumber() == booking.getRoom().getRoomNumber())
                 {
                	 roomManager.updateStatusBySystem(room, RoomStatus.returnStatus(2));
                     
                 }
             }
       break;
        case 3:
            System.out.print("Enter number of adults: ");
            int numberOfAdults = Input.GetInt();
            booking.setNumOfAdult(numberOfAdults);
            break;
         case 4:
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
    public void Remove_booking()
    {
    	Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date current = digiClock.getTime();//get booking datetime
        
    	  System.out.print("Enter the room number to remove booking: ");
    	  int roomNum = Input.GetInt();
    	  Booking booking = bookingDao.searchBookingByRoomNum(roomNum);
    	  if (booking.getCheckOutDatetime().before(current))
    	  {
    	     bookingDao.removeBooking(booking);//only allow to remove when the status is expired
    	  }
    	  else
    	  {
    	       System.out.println("This booking cannot be removed in this moment");
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
