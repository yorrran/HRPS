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
        int j;
        boolean flag=false;
        System.out.println("Please enter the selected roomtype:");
        String roomType = Input.GetString();
        System.out.println("roomType:"+roomType);
 
        for (j = 0; j < roomManager.getRoomDao().getAllRoom().size(); j++)
        {
            if (roomManager.getRoomDao().getAllRoom().get(j).getRoomType().getType().equals(roomType))
            {
                if (roomManager.getRoomDao().getAllRoom().get(j).getRoomStatus().equals(RoomStatus.returnStatus(1)))
                {

                	Calendar digiClock = Calendar.getInstance();
                    digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
                    Date checkInDate = digiClock.getTime();//get reservation datetime

                    System.out.print("Enter check out date(ddMMyyyy): ");
                    String checkOutString = Input.GetString();
                    Date checkOutDate = null;
                    try
                    {
                        checkOutDate = dateFormat.parse(checkOutString.toString());
                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                    } // get check out date
                    
                    
                    long diff = checkOutDate.getTime() - checkInDate.getTime();
                    int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
                    double price = numOfDays
                            * (roomManager.getRoomDao().getAllRoom().get(j).getRoomType().getPrice() + roomManager.getRoomDao().getAllRoom().get(j).getFacing().getPrice());// get
                    // price
                    System.out.print("Enter number of adults: ");
                    int numberOfAdults = Input.GetInt();

                    System.out.print("Enter number of children: ");
                    int numberOfChildren = Input.GetInt();

                    Booking booking = new Booking(cus_id, roomManager.getRoomDao().getAllRoom().get(j), price, checkInDate, checkOutDate,
                            numberOfAdults, numberOfChildren);
                    bookingDao.addbooking(booking);
                    flag=true;
                    break;
                }
            }
        }
        if(flag==false)
        System.out.println("Room is currently not available");
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
        Calendar now = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date bookingDatetime = digiClock.getTime();//get booking datetime
        Date current=now.getTime();
        
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
        for (int i = 0; i < reservationManager.getReservationDao().getAllReservation().size(); i++)
        {
            if (reservationManager.getReservationDao().getAllReservation().get(i).getIdentity().equals(roomNum))
            {
                if (current.before(reservationManager.getReservationDao().getAllReservation().get(i).getCheckOutDatetime()))
                {
                    reservationManager.addReservation();
                }
                else if (current.after(reservationManager.getReservationDao().getAllReservation().get(i).getCheckOutDatetime()))
                {
                    System.out.println("The booking is not allowed");
                }
                else
                {
                    System.out.println("How many days that need to be extended");
                    int extendDays = Input.GetInt();
                    
                    digiClock.add(Calendar.DATE, 1);
                    Date start=digiClock.getTime();
                    digiClock.add(Calendar.DATE, extendDays-1);
                    Date end = digiClock.getTime();
                   
                    if (start.before(reservationManager.getReservationDao().getAllReservation().get(i).getCheckInDatetime())&&start.after(current)&&!reservationManager.getReservationDao().getAllReservation().get(i).getIdentity().equals(cus_id))
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
               }
            }
        }
        break;
        case 2:
        	Room room = null;
             do
             {
                 System.out.print("Enter the room number: ");
                 int newRoomNum = Input.GetInt();
                 room =reservationManager.getRoomByRoomNum(newRoomNum);

                 if (!room.getRoomStatus().equals(RoomStatus.returnStatus(1)))
                 {
                     System.out.println("This room is unavailable now, please select another room.");
                     room = null;
                 }
             } while (room == null);//selecte the vacant room
             
             long diff = bookingDao.searchBookingByRoomNum(roomNum).getCheckOutDatetime().getTime() - current.getTime();
             int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));//get difference of days between check out and check in
             double price = numOfDays * (room.getRoomType().getPrice() + room.getFacing().getPrice());//get price for room type and facing type
             booking.setCheckInDatetime(current);
             booking.setRoom(room);
             booking.setPrice(price);
             
             for (int i = 0; i < bookingDao.getAllBooking().size(); i++)
             {
                 if (bookingDao.getAllBooking().get(i).getRoom().getRoomNumber() == booking.getRoom().getRoomNumber())
                 {
                     room = reservationManager.getRoomByRoomNum(bookingDao.getAllBooking().get(i).getRoom().getRoomNumber());
                     reservationManager.updateStatusBySystem(room, RoomStatus.returnStatus(1));
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
