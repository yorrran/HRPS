import java.util.ArrayList;

/**
 * Interface for bill data access object.
 */
public interface BillDao {
	/**
	 * Gets the bill list.
	 * 
	 * @return the bill list.
	 */
	ArrayList<Bill> getAllBill();

	/**
	 * Add a new bill to the bill list.
	 * 
	 * @param bill
	 *            The new bill to be added to the list.
	 */
	void addBill(Bill bill);

	/**
	 * Update the room charge.
	 * 
	 * @param roomNum
	 *            The room number of the bill to be updated.
	 * @param amount
	 *            The new room charge amount.
	 */
	void updateRoomCharge(int roomNum, double amount);

	/**
	 * Update the room service charge
	 * 
	 * @param roomNum
	 *            The room number of the bill to be updated.
	 * @param price
	 *            The new room service charge amount.
	 */
	void updateRoomServiceCharge(int roomNum, double price);

	/**
	 * Remove a bill from the bill list.
	 * 
	 * @param bill
	 *            The bill to be removed from the list.
	 */
	void removeBill(Bill bill);

	/**
	 * Write the current list data to file.
	 */
	void updateFile();
}