import java.util.ArrayList;


public class BookingDaoImpl implements BookingDao{
	ArrayList<Booking> bookingList = null;
	private static final String filename = "File/booking.dat";
	
	
	public ArrayList<Booking> getAllBooking()
	{
		return this.bookingList;
	}
	
	public void addbooking(Booking booking)
	{
		this.bookingList.add(booking);
	}
	
	public void updateBooking(String identity, Booking booking)
	{
		for (int i = 0; i < bookingList.size(); i++)
        {
            if (bookingList.get(i).getIdentity() == identity)
          	bookingList.set(i, booking);
	        }
	}

	public void updateFile() {
		DataIO.Write(filename, bookingList);
	}
}
