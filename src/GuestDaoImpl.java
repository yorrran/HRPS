import java.util.ArrayList;

/**
 * Guest data access object.
 */

public class GuestDaoImpl implements GuestDao {
	ArrayList<Guest> guestList;
	private static final String fileName = "File/Guest.dat";

	/**
	 * Class constructor. Guest is loaded from data file.
	 */

	public GuestDaoImpl() {
		guestList = DataIO.Read(fileName);
	}

	/**
	 * Returns the list of guest so that manager class can access it.
	 * 
	 * @return list of guest
	 */

	@Override
	public ArrayList<Guest> getAllGuest() {
		return this.guestList;
	}

	/**
	 * Add a new guest to the existing list.
	 * 
	 * @param guest
	 *            new guest.
	 */
	@Override
	public void addGuest(Guest newGuest) {
		this.guestList.add(newGuest);
	}

	/**
	 * Update a guest with new Guest.
	 * 
	 * @param oldId
	 *            The oldId of the guest to be updated.
	 * @param newGuest
	 *            The newGuest object of guest.
	 */
	@Override
	public void updateGuest(String oldId, Guest newGuest) {
		for (int i = 0; i < guestList.size(); i++) {
			if (guestList.get(i).getIdentity() == oldId)
				guestList.set(i, newGuest);
		}
	}

	/**
	 * Display the guest information for specific identity.
	 * 
	 * @param Identity
	 *            The Identity of guest's information should be displayed.
	 */
	@Override
	public void searchGuestbyId(String Identity) {
		boolean found = false;

		for (int i = 0; i < guestList.size(); i++) {
			if (Identity.equals(guestList.get(i).getIdentity())) {
				System.out
						.println("--------------------------------------------------"
								+ "\n" + "Identity : "
								+ guestList.get(i).getIdentity()
								+ "\n"
								+ "Name : "
								+ guestList.get(i).getName()
								+ "\n"
								+ "Country : "
								+ guestList.get(i).getCountry()
								+ "\n"
								+ "Gender : "
								+ guestList.get(i).getGender()
								+ "\n"
								+ "Address : "
								+ guestList.get(i).getAddress()
								+ "\n"
								+ "Nationality : "
								+ guestList.get(i).getNationality()
								+ "\n"
								+ "Phone Number : "
								+ guestList.get(i).getPhoneNum()
								+ "\n"
								+ "Email : "
								+ guestList.get(i).getEmail()
								+ "\n"
								+ "Credit Card Number : "
								+ guestList.get(i).getCreditNum()
								+ "\n"
								+ "--------------------------------------------------");
				found = true;
				break;
			}
		}

		if (!found)
			System.out.println("Customer not found");
	}

	/**
	 * Display the guest information for specific name.
	 * 
	 * @param name
	 *            The name of information of guest should be displayed.
	 */

	@Override
	public void searchGuestbyName(String name) {
		boolean found = false;

		for (int i = 0; i < guestList.size(); i++) {
			if ((name.toLowerCase()).equals(guestList.get(i).getName()
					.toLowerCase())) {
				System.out
						.println("--------------------------------------------------"
								+ "\n" + "Identity : "
								+ guestList.get(i).getIdentity()
								+ "\n"
								+ "Name : "
								+ guestList.get(i).getName()
								+ "\n"
								+ "Country : "
								+ guestList.get(i).getCountry()
								+ "\n"
								+ "Gender : "
								+ guestList.get(i).getGender()
								+ "\n"
								+ "Address : "
								+ guestList.get(i).getAddress()
								+ "\n"
								+ "Nationality : "
								+ guestList.get(i).getNationality()
								+ "\n"
								+ "Phone Number : "
								+ guestList.get(i).getPhoneNum()
								+ "\n"
								+ "Email : "
								+ guestList.get(i).getEmail()
								+ "\n"
								+ "Credit Card Number : "
								+ guestList.get(i).getCreditNum()
								+ "\n"
								+ "--------------------------------------------------");

				found = true;
				break;
			}
		}

		if (!found)
			System.out.println("Customer not found");
	}

	/**
	 * Write the current list data to file.
	 */
	public void updateFile() {
		DataIO.Write(fileName, guestList);
	}
}