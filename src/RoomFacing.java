import java.io.Serializable;

public class RoomFacing implements Serializable
{	
	/**
	 * The id of this facing
	 */
    private int facingId;
    /**
     * The type of this facing
     */
    private String facingType;
    /**
     * The price of this facing
     */
    private double price;

    /**
     * Creates a new facing
     * 
     * @param id
     * @param type
     * @param price
     */
    public RoomFacing(int id, String type, double price)
    {
        this.facingId = id;
        this.facingType = type;
        this.price = price;
    }
    
    /**
     * Gets the facing id
     * @return the facing id
     */
    public int getFacingId()
    {
        return facingId;
    }

    /**
     * Gets the facing type
     * @return the facing type
     */
    public String getFacingType()
    {
        return facingType;
    }
    
    /**
     * Gets the facing price
     * @return the facing price
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * Changes the facing price
     * @param price The facing price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
}