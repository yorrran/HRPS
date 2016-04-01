import java.io.Serializable;


public class Room implements Serializable{
	int roomNumber;
	RoomType roomType;
	RoomBedType bedType;
	boolean wifi;
	RoomFacing facing;
	boolean smoking;
	RoomStatus roomStatus;
	
	public Room(int roomNumber, RoomType roomType, RoomBedType bedType, RoomFacing facing, boolean wifi, boolean smoking, RoomStatus roomStatus)
	{
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.bedType = bedType;
		this.facing = facing;
		this.wifi = wifi;
		this.smoking = smoking;
		this.roomStatus = roomStatus;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public RoomBedType getBedType() {
		return bedType;
	}

	public void setBedType(RoomBedType bedType) {
		this.bedType = bedType;
	}

	public boolean getWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public RoomFacing getFacing() {
		return facing;
	}

	public void setFacing(RoomFacing facing) {
		this.facing = facing;
	}

	public boolean getSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}
	
}
