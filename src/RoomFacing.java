import java.io.Serializable;

public class RoomFacing implements Serializable
{
    private int facingId;
    private String facingType;
    private double price;

    public RoomFacing(int id, String type, double price)
    {
        this.facingId = id;
        this.facingType = type;
        this.price = price;
    }

    public int getFacingId()
    {
        return facingId;
    }

    public String getFacingType()
    {
        return facingType;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}