import java.io.Serializable;

/**
 * RoomService is a class that stores all the details of the room service provided.
 */
public class RoomService implements Serializable
{
    private int id;
    private String service;
    private double price;

    /**
     * Class constructor specifying the room service name and its price.
     *
     * @param id
     * @param service
     * @param price
     */
    public RoomService(int id, String service, double price)
    {
        this.id = id;
        this.service = service;
        this.price = price;
    }

    /**
     * Getter.
     *
     * @return
     */
    public String getService()
    {
        return service;
    }

    /**
     * Setter.
     *
     * @param service
     */
    public void setService(String service)
    {
        this.service = service;
    }

    /**
     * Getter.
     *
     * @return
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Setter.
     *
     * @param price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
}
