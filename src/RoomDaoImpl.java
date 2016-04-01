import java.util.ArrayList;

public class RoomDaoImpl implements RoomDao
{
    private ArrayList<Room> roomList = null;
    private ArrayList<RoomType> roomTypeList = null;
    private ArrayList<RoomFacing> roomFacingList = null;
    private static final String filename = "File/Room.dat";
    private static final String filename2 = "File/RoomType.dat";
    private static final String filename3 = "File/RoomFacing.dat";

    public RoomDaoImpl()
    {
        try
        {
            roomList = DataIO.Read(filename);
            roomTypeList = DataIO.Read(filename2);
            roomFacingList = DataIO.Read(filename3);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public ArrayList<Room> getAllRoom()
    {
        return this.roomList;
    }

    public ArrayList<RoomType> getAllRoomType()
    {
        return this.roomTypeList;
    }

    public ArrayList<RoomFacing> getAllRoomFacing()
    {
        return this.roomFacingList;
    }

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


    public void addRoomType(RoomType roomType)
    {
        this.roomTypeList.add(roomType);
    }

    public void addRoomFacing(RoomFacing roomFacing)
    {
        this.roomFacingList.add(roomFacing);
    }

    public void removeRoomType(RoomType roomType)
    {
        this.roomTypeList.remove(roomType);

        for (int i = 0; i < roomTypeList.size(); i++)
        {
            roomTypeList.get(i).setTypeId(i + 1);
        }
    }

//	public void updateRoom(Room room)
//	{
//		 for (int i = 0; i < roomList.size(); i++)
//	        {
//	            if (roomList.get(i).getRoomNumber() == room.getRoomNumber())
//	            {
//	            	roomList.set(i, room);
//	            	break;
//	            }
//	        }
//	}

    public void updateFile()
    {
        DataIO.Write(filename, roomList);
        DataIO.Write(filename2, roomTypeList);
        DataIO.Write(filename3, roomFacingList);
    }
}