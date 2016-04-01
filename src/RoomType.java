import java.io.Serializable;

public class RoomType implements Serializable
{
    private int typeId;
    private String type;
    private double price;

    public RoomType(int id, String type, double price)
    {
        this.typeId = id;
        this.type = type;
        this.price = price;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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