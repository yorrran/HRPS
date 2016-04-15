import java.util.ArrayList;
import java.util.Date;

/**
 * Interface for order data access object.
 */
public interface OrderDao {

	/**
	 * Returns the list of order so that manager class can access it.
	 * 
	 * @return the list of order.
	 */
	ArrayList<Order> getAllOrder();

	/**
	 * Add a new order to the existing list.
	 * 
	 * @param order
	 *            The new order.
	 */
	void addOrder(Order order);

	/**
	 * When a guest orders a room service, add the rooms service and its details
	 * if any to the list of room services ordered according to the room number.
	 * 
	 * @param roomNum
	 *            The room number of the guest that ordered a room service
	 * @param date
	 *            The current date and time when the order is made
	 * @param newRoomService
	 *            The room service that is ordered by the guest
	 * @param newRemark
	 *            The remarks on the room service
	 * @param newStatus
	 *            The status of the room service
	 */
	void addRoomServicetoOrder(int roomNum, Date date,
			RoomService newRoomService, String newRemark, OrderStatus newStatus);

	/**
	 * Remove a order.
	 * 
	 * @param order
	 *            The order to be removed
	 */
	void removeOrder(Order rst);

	/**
	 * Return the room services and its details ordered by a room. Null if the
	 * order does not exist.
	 * 
	 * @param roomNum
	 *            The room number of which order should be returned
	 * @return the order of the specified room number if it exist.
	 */
	Order getOrderByRoomNum(int roomNum);

	/**
	 * Write the current list data to file.
	 */
	void updateFile();
}