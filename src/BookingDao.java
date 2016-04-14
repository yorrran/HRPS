import java.util.ArrayList;

public interface BookingDao
{
    ArrayList<Booking> getAllBooking();

    void addBooking(Booking booking);

    Booking searchBookingByRoomNum(int BookNum);

    void removeBooking(Booking booking);

    void updateBooking(String identity, Booking booking);

    void displayBooking();

    void updateFile();
}