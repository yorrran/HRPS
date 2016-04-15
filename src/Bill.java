import java.io.Serializable;

/**
 * Bill is a class that stores all the details of the room service ordered by a
 * room.
 */
public class Bill implements Serializable {
	/**
	 * The bill number.
	 */
	private int billNum;

	/**
	 * Which room the bill belongs to.
	 */
	private int roomNumber;

	/**
	 * The rate of discount.
	 */
	private double discount;

	/**
	 * The total room charge.
	 */
	private double roomCharge;

	/**
	 * The total room service charge.
	 */
	private double roomServiceCharge;

	/**
	 * Tax amount in Singapore. 10% service charge. 7% GST. 1% government
	 * charge.
	 */
	private static final double tax = 0.18;

	/**
	 * Class constructor specifying the bill number, room number. Room charge,
	 * room service charge and discount will be 0 when initialized.
	 * 
	 * @param billNum
	 *            The bill number of the bill.
	 * @param roomNumber
	 *            Which room does this bill belongs to.
	 * @param roomCharge
	 *            The room charge.
	 * @param roomServiceCharge
	 *            The total room service charge.
	 * @param discount
	 *            The discount amount.
	 */
	public Bill(int billNum, int roomNumber, double roomCharge,
			double roomServiceCharge, double discount) {
		this.billNum = billNum;
		this.roomNumber = roomNumber;
		this.roomCharge = roomCharge;
		this.roomServiceCharge = roomServiceCharge;
		this.discount = discount;
	}

	/**
	 * Gets the bill number.
	 * 
	 * @return the bill number.
	 */
	public int getBillNum() {
		return billNum;
	}

	/**
	 * Gets the room number.
	 * 
	 * @return the room number.
	 */
	public int getRoomNum() {
		return roomNumber;
	}

	/**
	 * Set the room number of the bill.
	 * 
	 * @param roomNumber
	 *            The room number of the bill.
	 */
	public void setRoomNum(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * Gets the total room charge.
	 * 
	 * @return the room charge of the room.
	 */
	public double getRoomCharge() {
		return roomCharge;
	}

	/**
	 * Change the room charge of the room.
	 * 
	 * @param roomCharge
	 *            The new room charge.
	 */
	public void setRoomCharge(double roomCharge) {
		this.roomCharge = roomCharge;
	}

	/**
	 * Gets the total room service charge.
	 * 
	 * @return the room service charge of the room.
	 */
	public double getRoomServiceCharge() {
		return roomServiceCharge;
	}

	/**
	 * Change the room service charge of the room.
	 * 
	 * @param roomServiceCharge
	 *            The new room service charge.
	 */
	public void setRoomServiceCharge(double roomServiceCharge) {
		this.roomServiceCharge = roomServiceCharge;
	}

	/**
	 * Gets the tax.
	 * 
	 * @return the tax rate.
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * Gets the discount rate of the room.
	 * 
	 * @return the discount rate of the room.
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * Change the discount rate of the room.
	 * 
	 * @param discount
	 *            The new discount rate of the room.
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}