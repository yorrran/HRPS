import java.util.ArrayList;

public class DataSetup
{
	
	public static void main(String[] args)
    {
		CreateRoom();
    }
	
    public static void CreateRoomType()
    {
        String fileName = "File/RoomType.dat";
        ArrayList<RoomType> roomTypeList = new ArrayList();
        RoomType type1 = new RoomType(1, "Single", 300);
        RoomType type2 = new RoomType(2, "Double", 400);
        RoomType type3 = new RoomType(3, "Delux", 500);
        RoomType type4 = new RoomType(4, "VIP", 600);
        roomTypeList.add(type1);
        roomTypeList.add(type2);
        roomTypeList.add(type3);
        roomTypeList.add(type4);
        DataIO.Write(fileName, roomTypeList);
    }

    public static void CreateRoomFacing()
    {
        String fileName = "File/RoomFacing.dat";
        ArrayList<RoomFacing> roomFacingList = new ArrayList();
        RoomFacing facing1 = new RoomFacing(1, "East", 30);
        RoomFacing facing2 = new RoomFacing(2, "South", 20);
        RoomFacing facing3 = new RoomFacing(3, "West", 5);
        RoomFacing facing4 = new RoomFacing(4, "North", 5);
        roomFacingList.add(facing1);
        roomFacingList.add(facing2);
        roomFacingList.add(facing3);
        roomFacingList.add(facing4);
        DataIO.Write(fileName, roomFacingList);
    }

    public static void CreateRoom()
    {
        ArrayList<RoomType> roomTypeList = DataIO.Read("File/RoomType.dat");
        ArrayList<RoomFacing> roomFacingList = DataIO.Read("File/RoomFacing.dat");
        String fileName = "File/Room.dat";
        ArrayList<Room> roomList = new ArrayList();
        for (int i = 1; i <= 8; i++)
        {
            roomList.add(new Room((200 + i), roomTypeList.get(0), RoomBedType.returnRoomBedType(1), roomFacingList.get(0), false, false, RoomStatus.returnStatus(2)));
        }

        for (int i = 1; i <= 8; i++)
        {
            roomList.add(new Room((300 + i), roomTypeList.get(0), RoomBedType.returnRoomBedType(1), roomFacingList.get(1), true, false, RoomStatus.returnStatus(1)));
        }

        for (int i = 1; i <= 8; i++)
        {
            roomList.add(new Room((400 + i), roomTypeList.get(1), RoomBedType.returnRoomBedType(1), roomFacingList.get(2), true, false, RoomStatus.returnStatus(1)));
        }

        for (int i = 1; i <= 8; i++)
        {
            roomList.add(new Room((500 + i), roomTypeList.get(2), RoomBedType.returnRoomBedType(2), roomFacingList.get(3), true, false, RoomStatus.returnStatus(1)));
        }

        for (int i = 1; i <= 8; i++)
        {
            roomList.add(new Room((600 + i), roomTypeList.get(3), RoomBedType.returnRoomBedType(3), roomFacingList.get(2), true, false, RoomStatus.returnStatus(1)));
        }

        for (int i = 1; i <= 8; i++)
        {
            roomList.add(new Room((700 + i), roomTypeList.get(3), RoomBedType.returnRoomBedType(3), roomFacingList.get(2), true, true, RoomStatus.returnStatus(4)));
        }

        DataIO.Write(fileName, roomList);
    }
}