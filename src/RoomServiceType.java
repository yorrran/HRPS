import java.io.Serializable;

/**
 *
 */
public class RoomServiceType implements Serializable
{
    private int id;
    private String service;
    private double price;

    /**
     *
     * @param id
     * @param service
     * @param price
     */
    public RoomServiceType(int id, String service, double price)
    {
        this.id = id;
        this.service = service;
        this.price = price;
    }

    /**
     *
     * @return
     */
    public String getService()
    {
        return service;
    }

    /**
     *
     * @param service
     */
    public void setService(String service)
    {
        this.service = service;
    }

    /**
     *
     * @return
     */
    public double getPrice()
    {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
}
