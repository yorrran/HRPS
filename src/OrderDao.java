import java.util.ArrayList;
import java.util.Date;

public interface OrderDao
{
    ArrayList<Order> getAllOrder();

    void addOrder(Order order);

    void addRoomServicetoOrder(int roomNum, Date date, RoomServiceType newRoomService, String newRemark, OrderStatus newStatus);

    void removeOrder(Order rst);

    void updateOrder(Order order);

    Order getOrderByRoomNum(int roomNum);

    void updateFile();
}