import java.util.ArrayList;

public interface GuestDao
{
    ArrayList<Guest> getAllGuest();

    void addGuest(Guest g);

    void updateGuest(String oldId, Guest g);

    void searchGuestbyId(String Identity);

    void searchGuestbyName(String name);

    void updateFile();
}