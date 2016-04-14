import java.util.ArrayList;
import java.util.Date;

/**
 * Interface for order data access object.
 */
public interface OrderDao
{
    ArrayList<Order> getAllOrder();

    void addOrder(Order order);

    void addRoomServicetoOrder(int roomNum, Date date, RoomService newRoomService, String newRemark, OrderStatus newStatus);

    void removeOrder(Order rst);

    Order getOrderByRoomNum(int roomNum);

    void updateFile();
}