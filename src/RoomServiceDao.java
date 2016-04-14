import java.util.ArrayList;

/**
 *
 */
public interface RoomServiceDao
{
    ArrayList<RoomServiceType> getAllRoomService();

    void addRoomService(RoomServiceType rst);

    void updateRoomService(int index, RoomServiceType rst);

    void removeRoomService(RoomServiceType rst);

    void displayRoomService();

    void updateFile();
}