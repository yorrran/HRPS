import java.util.ArrayList;

public interface BookingDao
{		
	/**
	 * 
	 * @return
	 */
    ArrayList<Booking> getAllBooking();
    
    /**
     * 
     * @param booking
     */
    void addBooking(Booking booking);

    /**
     * 
     * @param BookNum
     * @return
     */
    Booking searchBookingByRoomNum(int BookNum);
    
    /**
     * 
     * @param booking
     */
    void removeBooking(Booking booking);
    
    /**
     * 
     * @param identity
     * @param booking
     */
    void updateBooking(String identity, Booking booking);
    
    /**
     * 
     */
    void displayBooking();
    
    /**
     * 
     */
    void updateFile();
}