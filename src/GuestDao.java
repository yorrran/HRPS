import java.util.ArrayList;

public interface GuestDao {
	/**
	 * Gets all guest objects.
	 * 
	 * @return the guest objects.
	 */
	ArrayList<Guest> getAllGuest();

	/**
	 * Creates guest .
	 * 
	 * @param g
	 *            The guest object.
	 */
	void addGuest(Guest g);

	/**
	 * Updates the guest.
	 * 
	 * @param oldId
	 *            The old identity.
	 * @param g
	 *            The guest object.
	 */
	void updateGuest(String oldId, Guest g);

	/**
	 * Prints the guest details by identity.
	 * 
	 * @param Identity
	 *            The guest identity.
	 */
	void searchGuestbyId(String Identity);

	/**
	 * Prints the guest details by name.
	 * 
	 * @param name
	 *            Use name to search for customer information.
	 */
	void searchGuestbyName(String name);

	/**
	 * Writes guest data.
	 */
	void updateFile();
}