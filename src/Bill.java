import java.io.Serializable;

/**
 * Bill is a  class that stores all the details of the room service ordered by a room.
 */
public class Bill implements Serializable
{
    private int billNum;
    private int roomNumber;
    private double discount;
    private double roomCharge;
    private double roomServiceCharge;
    private static final double tax = 0.18;

    /**
     * Class constructor specifying the bill number, room number. Room charge, room service charge and discount will be 0 when initialized.
     *
     * @param billNum
     * @param roomNumber
     * @param roomCharge
     * @param roomServiceCharge
     * @param discount
     */
    public Bill(int billNum, int roomNumber, double roomCharge, double roomServiceCharge, double discount)
    {
        this.billNum = billNum;
        this.roomNumber = roomNumber;
        this.roomCharge = roomCharge;
        this.roomServiceCharge = roomServiceCharge;
        this.discount = discount;
    }

    /**
     * Getter.
     *
     * @return
     */
    public int getBillNum()
    {
        return billNum;
    }

    /**
     * Getter.
     *
     * @return
     */
    public int getRoomNum()
    {
        return roomNumber;
    }

    /**
     * Setter.
     *
     * @param roomNumber
     */
    public void setRoomNum(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    /**
     * Getter.
     *
     * @return
     */
    public double getRoomCharge()
    {
        return roomCharge;
    }

    /**
     * Setter.
     *
     * @param roomCharge
     */
    public void setRoomCharge(double roomCharge)
    {
        this.roomCharge = roomCharge;
    }

    /**
     * Getter.
     *
     * @return
     */
    public double getRoomServiceCharge()
    {
        return roomServiceCharge;
    }

    /**
     * Setter.
     *
     * @param roomServiceCharge
     */
    public void setRoomServiceCharge(double roomServiceCharge)
    {
        this.roomServiceCharge = roomServiceCharge;
    }

    /**
     * Getter.
     *
     * @return
     */
    public double getTax()
    {
        return tax;
    }


    /**
     * Getter.
     *
     * @return
     */
    public double getDiscount()
    {
        return discount;
    }

    /**
     * Setter.
     *
     * @param discount
     */
    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
}