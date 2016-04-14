import java.util.ArrayList;
import java.util.Date;

/**
 * Order data access object.
 */
public class OrderDaoImpl implements OrderDao
{
    private static final String fileName = "File/Order.dat";
    private ArrayList<Order> orderList;

    /**
     * Class constructor. Order is loaded from data file.
     */
    public OrderDaoImpl()
    {
        orderList = DataIO.Read(fileName);
    }

    /**
     * Add a new order to the existing list.
     *
     * @param order new order
     */
    @Override
    public void addOrder(Order order)
    {
        orderList.add(order);
    }

    /**
     * When a guest orders a room service, add the rooms service and its details if any to the list of room services ordered according to the room
     * number.
     *
     * @param roomNum        room number of the guest that ordered a room service
     * @param date           the current date and time when the order is made
     * @param newRoomService room service that is ordered by the guest
     * @param newRemark      remarks on the room service
     * @param newStatus      status of the room service
     */
    @Override
    public void addRoomServicetoOrder(int roomNum, Date date, RoomService newRoomService, String newRemark, OrderStatus newStatus)
    {
        for (int i = 0; i < orderList.size(); i++)
        {
            if (orderList.get(i).getRoomNumber() == roomNum)
            {
                orderList.get(i).getRoomServiceList().add(newRoomService);
                orderList.get(i).getDateList().add(date);
                orderList.get(i).getRemarkList().add(newRemark);
                orderList.get(i).getStatusList().add(newStatus);
            }
        }
    }

    /**
     * Remove a order.
     *
     * @param order the order to be removed
     */
    @Override
    public void removeOrder(Order order)
    {
        orderList.remove(order);
    }

    /**
     * Return the room services and its details ordered by a room. Null if the order does not exist.
     *
     * @param roomNum the room number of which order should be returned
     * @return the order of the specified room number if it exist.
     */
    @Override
    public Order getOrderByRoomNum(int roomNum)
    {
        for (int i = 0; i < orderList.size(); i++)
        {
            if (orderList.get(i).getRoomNumber() == roomNum)
                return orderList.get(i);
        }

        System.out.println("Room does not exists.");
        return null;
    }

    /**
     * Returns the list of order so that manager class can access it.
     *
     * @return list of order
     */
    @Override
    public ArrayList<Order> getAllOrder()
    {
        return orderList;
    }

    /**
     * Write the current list data to file.
     */
    @Override
    public void updateFile()
    {
        DataIO.Write(fileName, orderList);
    }
}