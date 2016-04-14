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
     * @param roomNumber specify which room does this order belongs to
     */
    public Order(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    /**
     * Getter.
     *
     * @return the room number of this order list
     */
    public int getRoomNumber()
    {
        return roomNumber;
    }

    /**
     * Getter.
     *
     * @return the list of date of each room service ordered
     */
    public ArrayList<Date> getDateList()
    {
        return dateList;
    }

    /**
     * Getter.
     *
     * @return the list of room service ordered
     */
    public ArrayList<RoomService> getRoomServiceList()
    {
        return roomServiceList;
    }

    /**
     * Getter.
     *
     * @return
     */
    public ArrayList<String> getRemarkList()
    {
        return remarkList;
    }

    /**
     * Getter.
     *
     * @return
     */
    public ArrayList<OrderStatus> getStatusList()
    {
        return statusList;
    }
}