import java.io.Serializable;

public class Room implements Serializable
{
	/**
	 * The room number of this room.
	 */
    private int roomNumber;
    /**
     * The type of this room.
     */
    private RoomType roomType;
    /**
     * The bed type of this room.
     */
    private RoomBedType bedType;
    /**
     * The wifi status of this room.
     */
    private boolean wifi;
    /**
     * The facing type of this room.
     */
    private RoomFacing facing;
    /**
     * The smoking status of this room.
     */
    private boolean smoking;
    /**
     * The room status of this room.
     */
    private RoomStatus roomStatus;
    
    
    /**
     * Creates a new room.
     * 
     * @param roomNumber is the room number of this room.
     * @param roomType is the type of this room. 
     * @param bedType is the bed type of this room.
     * @param facing is the facing type of this room.
     * @param wifi is the wifi status of this room.
     * @param smoking is the smoking status of this room.
     * @param roomStatus is The room status of this room.
     */
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
    
    /**
     * Gets the room number.
     * @return the room number.
     */
    public int getRoomNumber()
    {
        return roomNumber;
    }
    
    /**
     * Changes the room number.
     * @param roomNumber The room number.
     */
    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }
    
    /**
     * Gets the room type.
     * @return the room type.
     */
    public RoomType getRoomType()
    {
        return roomType;
    }

    /**
     * Changes the room type.
     * @param roomType The room type.
     */
    public void setRoomType(RoomType roomType)
    {
        this.roomType = roomType;
    }
    
    /**
     * Gets the bed type.
     * @return the bed type.
     */
    public RoomBedType getBedType()
    {
        return bedType;
    }
    
    /**
     * Changes the bed type.
     * @param bedType The bed type.
     */
    public void setBedType(RoomBedType bedType)
    {
        this.bedType = bedType;
    }
    
    /**
     * Gets the wifi status.
     * @return the wifi status.
     */
    public boolean getWifi()
    {
        return wifi;
    }
    
    /**
     * Changes the wifi status.
     * @param wifi The wifi status.
     */
    public void setWifi(boolean wifi)
    {
        this.wifi = wifi;
    }
    
    /**
     * Gets the facing.
     * @return the facing. 
     */
    public RoomFacing getFacing()
    {
        return facing;
    }
    
    /**
     * Changes the facing.
     * @param facing The facing.
     */
    public void setFacing(RoomFacing facing)
    {
        this.facing = facing;
    }
    
    /**
     * Gets the smoking status.
     * @return the smoking status.
     */
    public boolean getSmoking()
    {
        return smoking;
    }
    
    /**
     * Changes the smoking status.
     * @param smoking The smoking status.
     */
    public void setSmoking(boolean smoking)
    {
        this.smoking = smoking;
    }
    
    /**
     * Gets the room status.
     * @return the room status.
     */
    public RoomStatus getRoomStatus()
    {
        return roomStatus;
    }
    
    /**
     * Changes the room status.
     * @param roomStatus The room status.
     */
    public void setRoomStatus(RoomStatus roomStatus)
    {
        this.roomStatus = roomStatus;
    }
}