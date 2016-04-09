import java.util.ArrayList;


public class ReservationDaoImpl implements ReservationDao
{
    private ArrayList<Reservation> reservationList = null;
    private static final String filename = "File/Reservation.dat";

    public ReservationDaoImpl()
    {
        reservationList = DataIO.Read(filename);
    }

    public ArrayList<Reservation> getAllReservation()
    {
        return this.reservationList;
    }

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

    public void addReservation(Reservation reservation)
    {
        this.reservationList.add(reservation);
    }

    public void removeReservation(Reservation reservation)
    {
        this.reservationList.remove(reservation);
    }

//	public void updateReservation(String reservationCode, Reservation reservation)
//	{
//		for (int i = 0; i < reservationList.size(); i++)
//        {
//            if (reservationList.get(i).getReservationCode() == reservationCode)
//            	reservationList.set(i, reservation);
//        }
//	}

    public void updateFile()
    {
        DataIO.Write(filename, reservationList);
    }
}