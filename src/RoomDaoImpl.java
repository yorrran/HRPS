import java.util.ArrayList;


public class RoomDaoImpl implements RoomDao
{	
	/**
	 * The array list of room.
	 */
    private ArrayList<Room> roomList = null;
    
    /**
     * The array list of room type.
     */
    private ArrayList<RoomType> roomTypeList = null;
    
    /**
     * The array list of room facing.
     */
    private ArrayList<RoomFacing> roomFacingList = null;
    
    /**
     * The file name of room data.
     */
    private static final String filename = "File/Room.dat";
    
    /**
     * The file name of room type data.
     */
    private static final String filename2 = "File/RoomType.dat";
    
    /**
     * The file name of room facing data.
     */
    private static final String filename3 = "File/RoomFacing.dat";
    
    /**
     * Creates room DAO.
     */
    public RoomDaoImpl()
    {
        try
        {
            roomList = DataIO.Read(filename);
            roomTypeList = DataIO.Read(filename2);
            roomFacingList = DataIO.Read(filename3);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Gets all room objects
     */
    public ArrayList<Room> getAllRoom()
    {
        return this.roomList;
    }
    
    /**
     * Gets all room type objects
     */
    public ArrayList<RoomType> getAllRoomType()
    {
        return this.roomTypeList;
    }
    
    /**
     * Gets all room facing objects
     */
    public ArrayList<RoomFacing> getAllRoomFacing()
    {
        return this.roomFacingList;
    }
    
    /**
     * Gets room object by room number.
     * @param roomNum The room number
     * @return the room object.
     */
    public Room getRoomByRoomNum(int roomNum)
    {
        Room temp = null;
        do
        {
            for (int i = 0; i < roomList.size(); i++)
            {
                if (roomNum == roomList.get(i).getRoomNumber())
                {
                    temp = roomList.get(i);
                    break;
                }
            }
            if (temp == null)
            {
                System.out.print("Room cannot find, please enter a correct room number: ");
                roomNum = Input.GetInt();
            }
        }
        while (temp == null);

        return temp;
    }

    /**
     * Creates room type.
     * @param roomType The room type.
     */
    public void addRoomType(RoomType roomType)
    {
        this.roomTypeList.add(roomType);
    }
    
    /**
     * Creates room facing.
     * @param roomFacing The room facing.
     */
    public void addRoomFacing(RoomFacing roomFacing)
    {
        this.roomFacingList.add(roomFacing);
    }
    
    /**
     * Removes room type.
     * @param roomType The room type.
     */
    public void removeRoomType(RoomType roomType)
    {
        this.roomTypeList.remove(roomType);

        for (int i = 0; i < roomTypeList.size(); i++)
        {
            roomTypeList.get(i).setTypeId(i + 1);
        }
    }
    
    /**
     * Writes room data.
     */
    public void updateFile()
    {
        DataIO.Write(filename, roomList);
        DataIO.Write(filename2, roomTypeList);
        DataIO.Write(filename3, roomFacingList);
    }
}