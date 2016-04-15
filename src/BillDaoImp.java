import java.util.ArrayList;

/**
 * Bill data access object.
 */
public class BillDaoImp implements BillDao {
	private static final String fileName = "File/Bill.dat";
	private static ArrayList<Bill> billList;

	/**
	 * Class constructor. Bill is loaded from data file.
	 */
	public BillDaoImp() {
		billList = DataIO.Read(fileName);
	}

	/**
	 * Add a new bill to the bill list.
	 * 
	 * @param bill
	 *            The new bill to be added to the list.
	 */
	@Override
	public void addBill(Bill bill) {
		billList.add(bill);
	}

	/**
	 * Update the room charge.
	 * 
	 * @param roomNum
	 *            The room number of the bill to be updated.
	 * @param amount
	 *            The new room charge amount.
	 */
	@Override
	public void updateRoomCharge(int roomNum, double amount) {
		for (int i = 0; i < billList.size(); i++) {
			if (billList.get(i).getRoomNum() == roomNum)
				billList.get(i).setRoomCharge(
						billList.get(i).getRoomServiceCharge() + amount);
		}
	}

	/**
	 * Update the room service charge
	 * 
	 * @param roomNum
	 *            The room number of the bill to be updated.
	 * @param price
	 *            The new room service charge amount.
	 */
	@Override
	public void updateRoomServiceCharge(int roomNum, double amount) {
		for (int i = 0; i < billList.size(); i++) {
			if (billList.get(i).getRoomNum() == roomNum)
				billList.get(i).setRoomServiceCharge(
						billList.get(i).getRoomServiceCharge() + amount);
		}
	}

	/**
	 * Remove a bill from the bill list.
	 * 
	 * @param bill
	 *            The bill to be removed from the list.
	 */
	@Override
	public void removeBill(Bill bill) {
		billList.remove(bill);
	}

	/**
	 * Returns the list of bill so that manager class can access it.
	 * 
	 * @return list of bills
	 */
	@Override
	public ArrayList<Bill> getAllBill() {
		return BillDaoImp.billList;
	}

	/**
	 * Write the current list data to file.
	 */
	@Override
	public void updateFile() {
		DataIO.Write(fileName, billList);
	}
}