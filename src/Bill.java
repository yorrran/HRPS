import java.io.Serializable;

/**
 *
 */
public class Bill implements Serializable
{
    private int billNum;
    private int roomNumber;
    private double roomCharge;
    private double roomServiceCharge;
    private double discount;
    private static final double tax = 0.18;

    /**
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
     *
     * @return
     */
    public int getBillNum()
    {
        return billNum;
    }

    /**
     *
     * @param billNum
     */
    public void setBillNum(int billNum)
    {
        this.billNum = billNum;
    }

    /**
     *
     * @return
     */
    public int getRoomNum()
    {
        return roomNumber;
    }

    /**
     *
     * @param roomNumber
     */
    public void setRoomNum(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    /**
     *
     * @return
     */
    public double getRoomCharge()
    {
        return roomCharge;
    }

    /**
     *
     * @param roomCharge
     */
    public void setRoomCharge(double roomCharge)
    {
        this.roomCharge = roomCharge;
    }

    /**
     *
     * @return
     */
    public double getRoomServiceCharge()
    {
        return roomServiceCharge;
    }

    /**
     *
     * @param roomServiceCharge
     */
    public void setRoomServiceCharge(double roomServiceCharge)
    {
        this.roomServiceCharge = roomServiceCharge;
    }

    /**
     *
     * @return
     */
    public double getTax()
    {
        return tax;
    }


    /**
     *
     * @return
     */
    public double getDiscount()
    {
        return discount;
    }

    /**
     *
     * @param discount
     */
    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
}