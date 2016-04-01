import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable
{
    private String reservationCode;
    private String identity;
    private Room room;
    private double price;
    private Date reservationDatetime;
    private Date checkInDatetime;
    private Date checkOutDatetime;
    private int numOfAdult;
    private int numOfChildren;
    private ReservationStatus reservationStatus;

    public Reservation(String reservationCode, String identity, Room room, double price, Date reservationDatetime, Date checkInDatetime, Date checkOutDatetime, int numOfAdult, int numOfChildren, ReservationStatus reservationStatus)
    {
        this.reservationCode = reservationCode;
        this.identity = identity;
        this.room = room;

        this.price = price;
        this.reservationDatetime = reservationDatetime;
        this.checkInDatetime = checkInDatetime;
        this.checkOutDatetime = checkOutDatetime;
        this.numOfAdult = numOfAdult;
        this.numOfChildren = numOfChildren;
        this.reservationStatus = reservationStatus;
    }

    public String getReservationCode()
    {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode)
    {
        this.reservationCode = reservationCode;
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

    public Date getReservationDatetime()
    {
        return reservationDatetime;
    }

    public void setReservationDatetime(Date reservationDatetime)
    {
        this.reservationDatetime = reservationDatetime;
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

    public ReservationStatus getReservationStatus()
    {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus)
    {
        this.reservationStatus = reservationStatus;
    }
}