import java.util.ArrayList;

public interface ReservationDao
{	
	/**
	 * Gets all reservation objects.
	 * @return The reservation objects.
	 */
    ArrayList<Reservation> getAllReservation();
    
    /**
     * Gets reservation by reservation code.
     * @param reservationCode The reservation code.
     * @return the reservation.
     */
    Reservation searchReservationByReservationCode(String reservationCode);
    
    /**
     * Creates reservation.
     * @param reservation The reservation object.
     */
    void addReservation(Reservation reservation);
    
    /**
     * Removes the reservation.
     * @param reservation The reservation object.
     */
    void removeReservation(Reservation reservation);
    
    /**
     * Writes reservation data.
     */
    void updateFile();
}
