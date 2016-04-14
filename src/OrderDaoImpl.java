import java.util.ArrayList;
import java.util.Date;

public class OrderDaoImpl implements OrderDao
{
    private ArrayList<Order> orderList;

    private static final String fileName = "File/Order.dat";

    public OrderDaoImpl()
    {
        orderList = DataIO.Read(fileName);
    }

    @Override
    public ArrayList<Order> getAllOrder()
    {
        return orderList;
    }

    @Override
    public void addOrder(Order order)
    {
        orderList.add(order);
    }

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

    @Override
    public void addRoomServicetoOrder(int roomNum, Date date, RoomServiceType newRoomService, String newRemark, OrderStatus newStatus)
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

    @Override
    public void updateFile()
    {
        DataIO.Write(fileName, orderList);
    }

    @Override
    public void removeOrder(Order order)
    {
        orderList.remove(order);
    }
}