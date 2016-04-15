import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable
{	
	/**
	 * The reservation code of this reservation.
	 */
    private String reservationCode;
    /**
     * The identity of customer.
     */
    private String identity;
    /**
     * The room of this reservation.
     */
    private Room room;
    /**
     * The price of this reservation.
     */
    private double price;
    /**
     * The date time of this reservation made.
     */
    private Date reservationDatetime;
    /**
     * The check in date time of this reservation.
     */
    private Date checkInDatetime;
    /**
     * The check out date time of this reservation. 
     */
    private Date checkOutDatetime;
    /**
     * The number of adult of this reservation.
     */
    private int numOfAdult;
    /**
     * The number of children of this reservation. 
     */
    private int numOfChildren;
    /**
     * The status of this reservation.
     */
    private ReservationStatus reservationStatus;

    /**
     * Creates reservation.
     * 
     * @param reservationCode The reservation code of this reservation. 
     * @param identity The identity of customer.
     * @param room The room of this reservation.
     * @param price The price of this reservation.
     * @param reservationDatetime The date time of this reservation made.
     * @param checkInDatetime The check in date time of this reservation.
     * @param checkOutDatetime The check out date time of this reservation. 
     * @param numOfAdult The number of adult of this reservation.
     * @param numOfChildren The number of children of this reservation. 
     * @param reservationStatus The status of this reservation.
     */
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

    /**
     * Gets the reservation code.
     * @return the reservation code.
     */
    public String getReservationCode()
    {
        return reservationCode;
    }

    /**
     * Changes the reservation code. 
     * @param reservationCode The reservation code.
     */
    public void setReservationCode(String reservationCode)
    {
        this.reservationCode = reservationCode;
    }
    
    /**
     * Gets the identity of customer.
     * @return the identity of customer.
     */
    public String getIdentity()
    {
        return identity;
    }

    /**
     * Changes the identity of customer in this reservation.
     * @param identity The identity of customer in this reservation.
     */
    public void setIdentity(String identity)
    {
        this.identity = identity;
    }
    
    /**
     * Gets the room of this reservation.
     * @return the room of this reservation.
     */
    public Room getRoom()
    {
        return room;
    }

    /**
     * Changes the room of this reservation.
     * @param room The room of this reservation.
     */
    public void setRoom(Room room)
    {
        this.room = room;
    }

    /**
     * Gets the price of this reservation.
     * @return the price of this reservation.
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Changes the price of this reservation.
     * @param price The price of this reservation.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Gets the date time of this reservation made.
     * @return the date time of this reservation made.
     */
    public Date getReservationDatetime()
    {
        return reservationDatetime;
    }

    /**
     * Changes the date time of this reservation made.
     * @param reservationDatetime The date time of this reservation made.
     */
    public void setReservationDatetime(Date reservationDatetime)
    {
        this.reservationDatetime = reservationDatetime;
    }

    /**
     * Gets the check in date time of this reservation.
     * @return the check in date time of this reservation.
     */
    public Date getCheckInDatetime()
    {
        return checkInDatetime;
    }

    /**
     * Changes the check in date time of this reservation.
     * @param checkInDatetime The check in date time of this reservation.
     */
    public void setCheckInDatetime(Date checkInDatetime)
    {
        this.checkInDatetime = checkInDatetime;
    }

    /**
     * Gets the check out date time of this reservation. 
     * @return the check out date time of this reservation. 
     */
    public Date getCheckOutDatetime()
    {
        return checkOutDatetime;
    }

    /**
     * Changes the check out date time of this reservation. 
     * @param checkOutDatetime The check out date time of this reservation. 
     */
    public void setCheckOutDatetime(Date checkOutDatetime)
    {
        this.checkOutDatetime = checkOutDatetime;
    }

    /**
     * Gets the number of adult of this reservation.
     * @return the number of adult of this reservation.
     */
    public int getNumOfAdult()
    {
        return numOfAdult;
    }

    /**
     * Changes the number of adult of this reservation.
     * @param numOfAdult The number of adult of this reservation.
     */
    public void setNumOfAdult(int numOfAdult)
    {
        this.numOfAdult = numOfAdult;
    }

    /**
     * Gets the number of children of this reservation. 
     * @return the number of children of this reservation. 
     */
    public int getNumOfChildren()
    {
        return numOfChildren;
    }

    /**
     * Changes the number of children of this reservation. 
     * @param numOfChildren The number of children of this reservation. 
     */
    public void setNumOfChildren(int numOfChildren)
    {
        this.numOfChildren = numOfChildren;
    }

    /**
     * Gets the status of this reservation.
     * @return the status of this reservation.
     */
    public ReservationStatus getReservationStatus()
    {
        return reservationStatus;
    }

    /**
     * Changes the status of this reservation.
     * @param reservationStatus The status of this reservation.
     */
    public void setReservationStatus(ReservationStatus reservationStatus)
    {
        this.reservationStatus = reservationStatus;
    }
}