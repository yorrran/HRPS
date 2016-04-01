import java.io.Serializable;

public class RoomServiceType implements Serializable
{
    private int id;
    private String service;
    private double price;

    public RoomServiceType(int id, String service, double price)
    {
        this.id = id;
        this.service = service;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
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
