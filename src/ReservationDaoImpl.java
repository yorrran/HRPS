import java.util.ArrayList;


public class ReservationDaoImpl implements ReservationDao
{
    private ArrayList<Reservation> reservationList = null;
    private static final String filename = "File/Reservation.dat";

    /**
     * Creates reservation DAO.
     */
    public ReservationDaoImpl()
    {
        reservationList = DataIO.Read(filename);
    }

    /**
	 * Gets all reservation objects.
	 * @return The reservation objects.
	 */
    public ArrayList<Reservation> getAllReservation()
    {
        return this.reservationList;
    }
    
    /**
     * Gets reservation by reservation code.
     * @param reservationCode The reservation code.
     * @return the reservation.
     */
    public Reservation searchReservationByReservationCode(String reservationCode)
    {
        Reservation temp = null;
        do
        {
            for (int i = 0; i < reservationList.size(); i++)
            {
                if (reservationCode.equals(reservationList.get(i).getReservationCode()))
                {
                    temp = reservationList.get(i);
                    break;
                }
            }

            if (temp == null)
            {
                System.out.print("Reservation cannot be found. Please enter a correct reservation code or 0 to cancel : ");
                reservationCode = Input.GetString();

                if (reservationCode.equals("0"))
                    break;
            }
        } while (temp == null);

        return temp;
    }

    
    /**
     * Creates reservation.
     * @param reservation The reservation object.
     */
    public void addReservation(Reservation reservation)
    {
        this.reservationList.add(reservation);
    }
    
    /**
     * Removes the reservation.
     * @param reservation The reservation object.
     */
    public void removeReservation(Reservation reservation)
    {
        this.reservationList.remove(reservation);
    }
    
    /**
     * Writes reservation data.
     */
    public void updateFile()
    {
        DataIO.Write(filename, reservationList);
    }
}