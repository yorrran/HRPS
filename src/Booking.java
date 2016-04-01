import java.util.ArrayList;
import java.util.Date;

public class Booking
{
    private String identity;
    private Room room;
    private double price;
    private Date bookingDatetime;
    private Date checkInDatetime;
    private Date checkOutDatetime;
    private int numOfAdult;
    private int numOfChildren;

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

    public String getIdentity()
    {
        return identity;
    }

    public void setIdentity(String identity)
    {
        this.identity = identity;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Date getBookingDatetime()
    {
        return bookingDatetime;
    }

    public void setBookingDatetime(Date bookingDatetime)
    {
        this.bookingDatetime = bookingDatetime;
    }

    public Date getCheckInDatetime()
    {
        return checkInDatetime;
    }

    public void setCheckInDatetime(Date checkInDatetime)
    {
        this.checkInDatetime = checkInDatetime;
    }

    public Date getCheckOutDatetime()
    {
        return checkOutDatetime;
    }

    public void setCheckOutDatetime(Date checkOutDatetime)
    {
        this.checkOutDatetime = checkOutDatetime;
    }

    public int getNumOfAdult()
    {
        return numOfAdult;
    }

    public void setNumOfAdult(int numOfAdult)
    {
        this.numOfAdult = numOfAdult;
    }

    public int getNumOfChildren()
    {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren)
    {
        this.numOfChildren = numOfChildren;
    }
}