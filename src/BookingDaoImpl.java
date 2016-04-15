import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Booking access object.
 */
public class BookingDaoImpl implements BookingDao {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	private ArrayList<Booking> bookingList = new ArrayList<Booking>();
	private static final String filename = "File/Booking.dat";

	/**
	 * Class constructor. Booking is loaded from data file.
	 */

	public BookingDaoImpl() {
		bookingList = DataIO.Read(filename);
	}

	/**
	 * Returns the list of booking so that manager class can access it.
	 * 
	 * @return list of booking.
	 */
	public ArrayList<Booking> getAllBooking() {
		return this.bookingList;
	}

	/**
	 * Return the booking and its details of room number. Null if the order does
	 * not exist.
	 * 
	 * @param roomNum
	 *            The roomNum of the booking need to be searched.
	 * @return booking the booking founded by using roomNum.
	 */
	public Booking searchBookingByRoomNum(int roomNum) {
		Booking temp = null;

		for (int i = 0; i < bookingList.size(); i++) {
			if (roomNum == bookingList.get(i).getRoom().getRoomNumber()) {
				temp = bookingList.get(i);
				break;
			}
		}

		if (temp == null)
			System.out.print("Booking can not find ");

		return temp;
	}

	/**
	 * Add a new booking to the existing list.
	 * 
	 * @param booking
	 *            new booking.
	 */

	@Override
	public void addBooking(Booking booking) {
		bookingList.add(booking);
		System.out.println("Booking is added Successfully! ");
	}

	/**
	 * Update a booking with new booking.
	 * 
	 * @param identity
	 *            The identity of the guest who makes the booking.
	 * @param booking
	 *            The booking object of guest.
	 */
	@Override
	public void updateBooking(String identity, Booking booking) {
		for (int i = 0; i < bookingList.size(); i++) {
			if (bookingList.get(i).getIdentity() == identity)
				bookingList.set(i, booking);
		}
	}

	/**
	 * Remove a booking.
	 * 
	 * @param booking
	 *            the booking to be removed.
	 */
	@Override
	public void removeBooking(Booking booking) {
		this.bookingList.remove(booking);
	}

	/**
	 * Display the booking information.
	 * 
	 */

	public void displayBooking() {
		System.out.println("Booking Information ");
		System.out.println("---------------------------------------");

		for (int i = 0; i < bookingList.size(); i++) {
			String identity = bookingList.get(i).getIdentity();
			Room room = bookingList.get(i).getRoom();
			double price = bookingList.get(i).getPrice();
			Date checkInDatetime = bookingList.get(i).getCheckInDatetime();
			Date checkOutDatetime = bookingList.get(i).getCheckOutDatetime();
			int numOfAdult = bookingList.get(i).getNumOfAdult();
			int numOfChildren = bookingList.get(i).getNumOfChildren();

			System.out.println("Customer Identity: " + identity);
			System.out.println("Room number: " + room.getRoomNumber()
					+ ", Price: " + price + ", Check In Date: "
					+ dateFormat.format(checkInDatetime) + ", Check Out Date: "
					+ dateFormat.format(checkOutDatetime));
			System.out.println("Number of adult: " + numOfAdult
					+ ", number of children: " + numOfChildren);
			System.out.println("---------------------------------------");
		}
	}

	/**
	 * Write the current list data to file.
	 */

	public void updateFile() {
		DataIO.Write(filename, bookingList);
	}
}