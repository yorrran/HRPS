import java.util.ArrayList;

/**
 * Booking access object.
 */

public interface BookingDao {
	/**
	 * Gets all booking objects.
	 * 
	 * @return The booking objects.
	 */
	ArrayList<Booking> getAllBooking();

	/**
	 * Add a new booking to the existing list.
	 * 
	 * @param booking
	 */
	void addBooking(Booking booking);

	/**
	 * Return the booking and its details of room number. Null if the order does
	 * not exist.
	 * 
	 * @param BookNum
	 *            The bookNum needs to be used for searching the booking.
	 * @return Booking The booking founde
	 */
	Booking searchBookingByRoomNum(int BookNum);

	/**
	 * Remove a booking.
	 * 
	 * @param booking
	 */
	void removeBooking(Booking booking);

	/**
	 * Update a booking with new booking.
	 * 
	 * @param identity
	 *            The identity of the guest who makes the booking.
	 * @param booking
	 */
	void updateBooking(String identity, Booking booking);

	/**
	 * Display the booking information.
	 */
	void displayBooking();

	/**
	 * Write the current list data to file.
	 */
	void updateFile();
}