import java.io.Serializable;

public class Bill implements Serializable
{
    private int billNum;
    private int roomNumber;
    private double roomCharge;
    private double roomServiceCharge;
    private double discount;
    private static final double tax = 0.18;

    public Bill(int billNum, int roomNumber, double roomCharge, double roomServiceCharge, double discount)
    {
        this.billNum = billNum;
        this.roomNumber = roomNumber;
        this.roomCharge = roomCharge;
        this.roomServiceCharge = roomServiceCharge;
        this.discount = discount;
    }

    public int getBillNum()
    {
        return billNum;
    }

    public void setBillNum(int billNum)
    {
        this.billNum = billNum;
    }

    public int getRoomNum()
    {
        return roomNumber;
    }

    public void setRoomNum(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public double getRoomCharge()
    {
        return roomCharge;
    }

    public void setRoomCharge(double roomCharge)
    {
        this.roomCharge = roomCharge;
    }

    public double getRoomServiceCharge()
    {
        return roomServiceCharge;
    }

    public void setRoomServiceCharge(double roomServiceCharge)
    {
        this.roomServiceCharge = roomServiceCharge;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
}