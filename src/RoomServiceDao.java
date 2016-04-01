import java.util.ArrayList;

public interface RoomServiceDao
{
    ArrayList<RoomServiceType> getAllRoomService();

    void addRoomService(RoomServiceType rst);

    void updateRoomService(RoomServiceType rst);

    void removeRoomService(RoomServiceType rst);

    void displayRoomService();

    void updateFile();
}