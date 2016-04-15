import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable
{
	/**
	 * 
	 */
    private String identity;
    /**
     * 
     */
    private Room room;
    
    /**
     * 
     */
    private double price;
    
    /**
     * 
     */
    private Date checkInDatetime;
    
    /**
     * 
     */
    private Date checkOutDatetime;
    
    /**
     * 
     */
    private int numOfAdult;
    
    /**
     * 
     */
    private int numOfChildren;
    
    /**
     * 
     * @param identity
     * @param room
     * @param price
     * @param checkInDatetime
     * @param checkOutDatetime
     * @param numOfAdult
     * @param numOfChildren
     */
    public Booking(String identity, Room room, double price, Date checkInDatetime, Date checkOutDatetime, int numOfAdult, int numOfChildren)
    {
        this.identity = identity;
        this.room = room;
        this.price = price;
        this.checkInDatetime = checkInDatetime;
        this.checkOutDatetime = checkOutDatetime;
        this.numOfAdult = numOfAdult;
        this.numOfChildren = numOfChildren;
    }
    
    /**
     * 
     * @return
     */
    public String getIdentity()
    {
        return identity;
    }
    
    /**
     * 
     * @param identity
     */
    public void setIdentity(String identity)
    {
        this.identity = identity;
    }
    
    /**
     * 
     * @return
     */
    public Room getRoom()
    {
        return room;
    }
    
    /**
     * 
     * @param room
     */
    public void setRoom(Room room)
    {
        this.room = room;
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
    
    /**
     * 
     * @return
     */
    public Date getCheckInDatetime()
    {
        return checkInDatetime;
    }
    
    /**
     * 
     * @param checkInDatetime
     */
    public void setCheckInDatetime(Date checkInDatetime)
    {
        this.checkInDatetime = checkInDatetime;
    }
    
    /**
     * 
     * @return
     */
    public Date getCheckOutDatetime()
    {
        return checkOutDatetime;
    }
    
    /**
     * 
     * @param checkOutDatetime
     */
    public void setCheckOutDatetime(Date checkOutDatetime)
    {
        this.checkOutDatetime = checkOutDatetime;
    }
    
    /**
     * 
     * @return
     */
    public int getNumOfAdult()
    {
        return numOfAdult;
    }
    
    /**
     * 
     * @param numOfAdult
     */
    public void setNumOfAdult(int numOfAdult)
    {
        this.numOfAdult = numOfAdult;
    }
    
    /**
     * 
     * @return
     */
    public int getNumOfChildren()
    {
        return numOfChildren;
    }
    
    /**
     * 
     * @param numOfChildren
     */
    public void setNumOfChildren(int numOfChildren)
    {
        this.numOfChildren = numOfChildren;
    }
}