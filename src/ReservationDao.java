import java.util.ArrayList;


public interface ReservationDao {
	ArrayList<Reservation> getAllReservation();
	Reservation searchReservationByReservationCode(String reservationCode);
	void addReservation(Reservation reservation);
	void removeReservation(Reservation reservation);
	//void updateReservation(String reservationCode, Reservation reservation);
	void updateFile();
}
