import java.util.ArrayList;


public class ReservationDaoImpl implements ReservationDao{

	ArrayList<Reservation> reservationList = null;
	private ArrayList<Room> roomList = null;
	private static final String filename = "File/Reversation.dat";
	
	public ReservationDaoImpl()
	{
		try
		{
			this.reservationList = DataIO.Read(filename);
		}
		catch(Exception ex)
		{
			System.out.println("No reservation recode");
		}
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
		for(int i = 0; i< reservationList.size();i++)
		{
			if(reservationCode.equals(reservationList.get(i).getReservationCode()))
			{
				temp = reservationList.get(i);
				break;
			}
		}
		if(temp == null)
		{
			System.out.print("Reservation cannot find, please enter a correct reservation code: ");
			reservationCode = Input.GetString();
		}
		}
		while(temp == null);
		return temp;
	}
	
	public void addReservation(Reservation reservation) {
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
	
	
	public void updateFile() {
		DataIO.Write(filename, reservationList);
	}
	
}
