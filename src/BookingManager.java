import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class BookingManager
{
    private ArrayList<Booking> bookingList;
    private BookingDao bookingDao;
    private ArrayList<Guest> guestList;
    private GuestManager guestManager;
    private ReservationManager reservationManager;
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> reservationList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

    public BookingManager(ArrayList<Booking> bookingList, ArrayList<Guest> guestList, GuestManager GuestManager,
                          ArrayList<Room> roomList, ArrayList<Reservation> reservationList, ReservationManager reservationManager)
    {
        bookingDao = new BookingDaoImpl();
        this.bookingList = bookingList;
        this.guestList = guestList;
        this.guestManager = GuestManager;
        this.roomList = roomList;
        this.reservationList = reservationList;
        this.reservationManager = reservationManager;
    }

    public void cus_type()
    {
        System.out.println("Is the customer walk-in or reservation?");
        System.out.println("Press 1 for walk-in customer");
        System.out.println("Press 2 for walk-in customer");
        //boolean flag = false;
        int cus_type = Input.GetInt();
        int i;
        System.out.println("Please enter customer identity");
        String cus_id = Input.GetString();
        if (cus_type == 1)
        {

            for (i = 0; i < guestList.size(); i++)
            {
                if (guestList.get(i).getIdentity() == cus_id)
                    System.out.println("Customer already exists");
            }
            if (i == guestList.size())
            {
                guestManager.addGuest();
                //flag = true;
                walkin_cus(cus_id);
            }
        }
        else
        {
            reserve_cus(cus_id);
        }
    }

    public void walkin_cus(String cus_id)
    {
        int i, j;
        System.out.println("Please enter the selected roomtype:");
        String room_type = Input.GetString();
        for (j = 0; j < roomList.size(); j++)
        {
            if (roomList.get(j).getRoomType().equals(room_type))
            {
                if (roomList.get(j).getRoomStatus().equals(RoomStatus.returnStatus(1)))
                {

                    System.out.print("Enter check in date(ddMMyyyy): ");
                    String checkInString = Input.GetString();
                    Date checkinDate = null;
                    try
                    {
                        checkinDate = dateFormat.parse(checkInString.toString());
                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                    } // get check in date

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

                    long diff = checkOutDate.getTime() - checkinDate.getTime();
                    int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
                    double price = numOfDays
                            * (roomList.get(j).getRoomType().getPrice() + roomList.get(j).getFacing().getPrice());// get
                    // price
                    System.out.print("Enter number of adults: ");
                    int numberOfAdults = Input.GetInt();

                    System.out.print("Enter number of children: ");
                    int numberOfChildren = Input.GetInt();

                    Booking booking = new Booking(cus_id, roomList.get(j), price, checkinDate, checkOutDate,
                            numberOfAdults, numberOfChildren);
                    bookingDao.addbooking(booking);
                }
                System.out.println("Room is currently not available");
            }
        }

    }

    public void reserve_cus(String cus_id)
    {
        for (int i = 0; i < reservationList.size(); i++)
        {
            if (reservationList.get(i).getIdentity() == cus_id)
            {
                Booking booking = new Booking(cus_id, reservationList.get(i).getRoom(),
                        reservationList.get(i).getPrice(), reservationList.get(i).getCheckInDatetime(),
                        reservationList.get(i).getCheckOutDatetime(), reservationList.get(i).getNumOfAdult(),
                        reservationList.get(i).getNumOfAdult());
                bookingDao.addbooking(booking);
            }

        }

    }

    public void update_booking(String cus_id)
    {
        Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date bookingDatetime = digiClock.getTime();//get booking datetime
        for (int i = 0; i < reservationList.size(); i++)
        {
            if (reservationList.get(i).getIdentity() == cus_id)
            {
                if (bookingDatetime.before(reservationList.get(i).getCheckOutDatetime()))
                {
                    reservationManager.addReservation();
                }
                else if (bookingDatetime.after(reservationList.get(i).getCheckOutDatetime()))
                {
                    System.out.println("The booking is not allowed");
                }
                else
                {
                    System.out.println("How many days that need to be extended");
                    int extend_days = Input.GetInt();
                    digiClock.add(Calendar.DATE, extend_days);
                    Date new_booking_Date = digiClock.getTime();
                    long diff = reservationList.get(i).getCheckInDatetime().getTime() - new_booking_Date.getTime();
                    if (diff < extend_days && reservationList.get(i).getIdentity() != cus_id)
                    {
                        System.out.println("The room is reserved by other customers");
                        System.out.println("Would you like to book another room");
                        System.out.println("1.book another room");
                        System.out.println("2.cancle booking");
                        int choice = Input.GetInt();
                        if (choice == 1)
                        {
                            walkin_cus(cus_id);
                        }

                    }


                }
            }
        }
    }

}
