import java.util.ArrayList;

public interface RoomDao
{
	ArrayList<Room> getAllRoom();
	ArrayList<RoomType> getAllRoomType();
	ArrayList<RoomFacing> getAllRoomFacing();
	Room getRoomByRoomNum(int roomNum);
	void addRoomType(RoomType roomType);
	void removeRoomType(RoomType roomType);
	void addRoomFacing(RoomFacing roomFacing);
	//void updateRoom(Room room);
	void updateFile();
}
