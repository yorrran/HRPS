import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 */
public class Order implements Serializable
{
    private int roomNumber;
    private ArrayList<Date> dateList = new ArrayList<>();
    private ArrayList<RoomServiceType> roomServiceList = new ArrayList<>();
    private ArrayList<OrderStatus> statusList = new ArrayList<>();
    private ArrayList<String> remarkList = new ArrayList<>();

    /**
     *
     * @param roomNumber
     */
    public Order(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    /**
     *
     * @return
     */
    public int getRoomNumber()
    {
        return roomNumber;
    }

    /**
     *
     * @return
     */
    public ArrayList<Date> getDateList()
    {
        return dateList;
    }

    /**
     *
     * @return
     */
    public ArrayList<RoomServiceType> getRoomServiceList()
    {
        return roomServiceList;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getRemarkList()
    {
        return remarkList;
    }

    /**
     *
     * @return
     */
    public ArrayList<OrderStatus> getStatusList()
    {
        return statusList;
    }
}