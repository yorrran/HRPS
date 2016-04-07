import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable
{
    private int roomNumber;
    private ArrayList<Date> dateList = new ArrayList<>();
    private ArrayList<RoomServiceType> roomServiceList = new ArrayList<>();
    private ArrayList<OrderStatus> statusList = new ArrayList<>();
    private ArrayList<String> remarkList = new ArrayList<>();

    public Order(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public ArrayList<Date> getDateList()
    {
        return dateList;
    }

    public ArrayList<RoomServiceType> getRoomServiceList()
    {
        return roomServiceList;
    }

    public ArrayList<String> getRemarkList()
    {
        return remarkList;
    }

    public ArrayList<OrderStatus> getStatusList()
    {
        return statusList;
    }
}