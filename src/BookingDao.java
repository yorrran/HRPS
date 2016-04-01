import java.util.ArrayList;

public interface BookingDao
{
    ArrayList<Booking> getAllBooking();

    void addbooking(Booking booking);

    void updateBooking(String identity, Booking booking);

    void updateFile();
}