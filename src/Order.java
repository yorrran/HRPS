import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Order is a class that stores all the details of the room service ordered by a room.
 */
public class Order implements Serializable
{
    private int roomNumber;
    private ArrayList<Date> dateList = new ArrayList<>();
    private ArrayList<RoomService> roomServiceList = new ArrayList<>();
    private ArrayList<OrderStatus> statusList = new ArrayList<>();
    private ArrayList<String> remarkList = new ArrayList<>();

    /**
     * Class constructor specifying the room it is attached to based on room number.
     *
     * @param roomNumber The room number of the room this order belongs to.
     */
    public Order(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    /**
     * Gets the room number of this order list.
     *
     * @return the room number of this order list.
     */
    public int getRoomNumber()
    {
        return roomNumber;
    }

    /**
     * Gets the list of date of each room service ordered.
     *
     * @return the list of date of each room service ordered.
     */
    public ArrayList<Date> getDateList()
    {
        return dateList;
    }

    /**
     * Gets the list of room service ordered.
     *
     * @return the list of room service ordered.
     */
    public ArrayList<RoomService> getRoomServiceList()
    {
        return roomServiceList;
    }

    /**
     * Gets the list of remarks of each room service ordered.
     *
     * @return the list of remarks of each room service.
     */
    public ArrayList<String> getRemarkList()
    {
        return remarkList;
    }

    /**
     * Gets the list of status of each room service ordered.
     *
     * @return the list of status for each room service.
     */
    public ArrayList<OrderStatus> getStatusList()
    {
        return statusList;
    }
}