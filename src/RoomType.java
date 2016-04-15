import java.io.Serializable;

public class RoomType implements Serializable
{
	/**
	 * The id of this room type.
	 */
    private int typeId;
    /**
     * The name of this room type.
     */
    private String type;
    /**
     * The price of this room type.
     */
    private double price;
    
    /**
     * Creates a new room type.
     * 
     * @param id The id of the room type.
     * @param type The type of the room.
     * @param price The price of the room.
     */
    public RoomType(int id, String type, double price)
    {
        this.typeId = id;
        this.type = type;
        this.price = price;
    }

    /**
     * Gets the room type id.
     * @return the room type id.
     */
    public int getTypeId()
    {
        return typeId;
    }
    
    /**
     * Changes the room type id.
     * @param typeId The room type id.
     */
    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }
    
    /**
     * Gets the room type name.
     * @return the room type name.
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * Changes the room type name.
     * @param type The room type name.
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
    /**
     * Gets the room type price.
     * @return the room type price.
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * Changes the room type price.
     * @param price The room type price.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
}