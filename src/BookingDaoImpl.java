import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookingDaoImpl implements BookingDao
{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
    private ArrayList<Booking> bookingList = new ArrayList<Booking>();
    private static final String filename = "File/booking.dat";

    public BookingDaoImpl()
    {
        bookingList = DataIO.Read(filename);
    }
    
    public ArrayList<Booking> getAllBooking()
    {
        return this.bookingList;
    }

    public Booking searchBookingByRoomNum(int roomNum)
    {
        Booking temp = null;
 
            for (int i = 0; i < bookingList.size(); i++)
            {
                if (roomNum==bookingList.get(i).getRoom().roomNumber)
                {
                    temp = bookingList.get(i);
                    break;
                }
            }

            if (temp == null)
            {
                System.out.print("Booking can not find ");
            }

        return temp;
    }

    public void addbooking(Booking booking)
    {
    	 bookingList.add(booking);
         System.out.println("Booking is added Successfully! ");
    }

    public void updateBooking(String identity, Booking booking)
    {
        for (int i = 0; i < bookingList.size(); i++)
        {
            if (bookingList.get(i).getIdentity() == identity)
                bookingList.set(i, booking);
        }
    }
    public void removeBooking(Booking booking)
    {
        this.bookingList.remove(booking);
    }
    public void displayBooking()
    {
    	for(int i=0;i<bookingList.size();i++)
    	{
         String identity = bookingList.get(i).getIdentity();
         Room room = bookingList.get(i).getRoom();
         double price = bookingList.get(i).getPrice();
         Date checkInDatetime =  bookingList.get(i).getCheckInDatetime();
         Date checkOutDatetime =  bookingList.get(i).getCheckOutDatetime();
         int numOfAdult =  bookingList.get(i).getNumOfAdult();
         int numOfChildren =bookingList.get(i).getNumOfChildren();

         System.out.println("Customer Identity: " + identity );
         System.out.println("Room number: " + room.getRoomNumber() + ", Price: " + price + ", Check In Date: " + dateFormat.format(checkInDatetime) + ", Check Out Date: " + dateFormat.format(checkOutDatetime));
         System.out.println("Number of adult: " + numOfAdult + ", number of children: " + numOfChildren);
         System.out.println("---------------------------------------");
     }
    }


    public void updateFile()
    {
        DataIO.Write(filename, bookingList);
    }
}