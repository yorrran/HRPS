import java.util.ArrayList;

/**
 * Interface for room service data access object.
 */
public interface RoomServiceDao
{
    ArrayList<RoomService> getAllRoomService();

    void addRoomService(RoomService rst);

    void updateRoomService(int index, RoomService rst);

    void removeRoomService(RoomService rst);

    void displayRoomService();

    void updateFile();
}