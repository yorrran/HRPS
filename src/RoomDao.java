import java.util.ArrayList;

public interface RoomDao
{	
	/**
	 * Gets all room objects.
	 * @return the room objects.
	 */
    ArrayList<Room> getAllRoom();
    
    /**
     * Gets all room types.
     * @return the room types.
     */
    ArrayList<RoomType> getAllRoomType();
    
    /**
     * Gets all room facing.
     * @return the room facing.
     */
    ArrayList<RoomFacing> getAllRoomFacing();
    
    /**
     * Gets room object by room number.
     * @param roomNum The room number.
     * @return the room object.
     */
    Room getRoomByRoomNum(int roomNum);
    
    /**
     * Creates room type.
     * @param roomType The room type.
     */
    void addRoomType(RoomType roomType);
    
    /**
     * Removes room type.
     * @param roomType The room type.
     */
    void removeRoomType(RoomType roomType);
    
    /**
     * Creates room facing.
     * @param roomFacing The room facing.
     */
    void addRoomFacing(RoomFacing roomFacing);
    
    /**
     * Writes room data.
     */
    void updateFile();
}
