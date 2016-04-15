import java.util.Scanner;

public class GuestManager {
	GuestDao guestDao;
	Scanner sc = new Scanner(System.in);

	/**
	 * Creates guest manager
	 */
	public GuestManager() {
		guestDao = new GuestDaoImpl();
	}

	/**
	 * Prints all guest details
	 */
	public void displayAllGuest() {
		for (int i = 0; i < guestDao.getAllGuest().size(); i++) {
			System.out
					.println("--------------------------------------------------"
							+ "\n" + "Identity : "
							+ guestDao.getAllGuest().get(i).getIdentity()
							+ "\t"
							+ "Name : "
							+ guestDao.getAllGuest().get(i).getName()
							+ "\t"
							+ "Country : "
							+ guestDao.getAllGuest().get(i).getCountry()
							+ "\t"
							+ "Gender : "
							+ guestDao.getAllGuest().get(i).getGender()
							+ "\t"
							+ "Address : "
							+ guestDao.getAllGuest().get(i).getAddress()
							+ "\t"
							+ "Nationality : "
							+ guestDao.getAllGuest().get(i).getNationality()
							+ "\t"
							+ "Phone Number : "
							+ guestDao.getAllGuest().get(i).getPhoneNum()
							+ "\n"
							+ "Email : "
							+ guestDao.getAllGuest().get(i).getEmail()
							+ "\t"
							+ "Credit Card Number : "
							+ guestDao.getAllGuest().get(i).getCreditNum()
							+ "\n"
							+ "--------------------------------------------------");
		}
	}

	/**
	 * Creates guest
	 */
	public void addGuest() {
		boolean flag = true;

		System.out.print("Enter customer identity : ");
		String identity = Input.GetString();

		for (int i = 0; i < guestDao.getAllGuest().size(); i++) {
			if (identity.equals(guestDao.getAllGuest().get(i).getIdentity())) {
				System.out.println("Customer already exists!");
				flag = false;
				break;
			}
		}

		if (flag == true) {
			System.out.print("Customer name : ");
			String name = sc.nextLine();
			System.out.print("Country : ");
			String country = sc.nextLine();
			System.out.print("Gender : ");
			String gender = Input.GetString();
			System.out.print("Address : ");
			String address = sc.nextLine();
			System.out.print("Nationality : ");
			String nationality = Input.GetString();
			System.out.print("Phone Number : ");
			int phoneNum = Input.GetInt();
			System.out.print("Email : ");
			String email = Input.GetString();
			System.out.print("Credit Card No. : ");
			long credit_no = Input.GetLong();
			Guest newGuest = new Guest(identity, name, country, gender,
					address, nationality, phoneNum, email, credit_no);

			guestDao.addGuest(newGuest);
		}
	}

	/**
	 * Updates guest
	 */
	public void updateGuest() {
		Boolean flag = false;

		System.out.println("Please Enter customer Identity : ");
		String guest_id = Input.GetString();

		for (int i = 0; i < guestDao.getAllGuest().size(); i++) {
			if (guest_id.equals(guestDao.getAllGuest().get(i).getIdentity())) {
				Guest guest = guestDao.getAllGuest().get(i);
				String Identity = guestDao.getAllGuest().get(i).getIdentity();
				System.out
						.println("Choose customer information to be updated or 0 to end: ");
				System.out.println("1. Identity");
				System.out.println("2. Name");
				System.out.println("3. Country");
				System.out.println("4. Gender");
				System.out.println("5. Address");
				System.out.println("6. Nationality");
				System.out.println("7. Phone Number");
				System.out.println("8. Email");
				System.out.println("9. Credit Card");
				System.out.println("0. Finish");

				int choice;

				do {
					System.out
							.println("Choose customer information to be updated or 0 to end: ");
					choice = Input.GetInt();

					switch (choice) {
					case 1:
						while (flag == false) {
							System.out.print("Enter new identity : ");
							String new_identity = Input.GetString();

							for (int j = 0; j < guestDao.getAllGuest().size(); j++) {
								if (new_identity.equals(guestDao.getAllGuest()
										.get(j).getIdentity())) {
									System.out
											.println("Please re-enter the id : ");
									break;
								} else {
									flag = true;
								}
							}
							if (flag == true)
								guest.setIdentity(new_identity);

						}
						break;
					case 2:
						System.out.print("Enter new name : ");
						String new_name = sc.nextLine();
						guest.setName(new_name);
						break;
					case 3:
						System.out.print("Enter new country : ");
						String new_country = sc.nextLine();
						guest.setCountry(new_country);
						break;
					case 4:
						System.out.print("Enter new gender : ");
						String new_gender = Input.GetString();
						guest.setGender(new_gender);
						break;
					case 5:
						System.out.print("Enter new address : ");
						String new_address = sc.nextLine();
						;
						guest.setAddress(new_address);
						break;
					case 6:
						System.out.print("Enter new nationality : ");
						String new_nation = Input.GetString();
						guest.setNationality(new_nation);
						break;
					case 7:
						System.out.print("Enter new phone number : ");
						int new_num = Input.GetInt();
						guest.setPhoneNum(new_num);
						break;
					case 8:
						System.out.print("Enter new email: ");
						String new_email = Input.GetString();
						guest.setEmail(new_email);
						break;
					case 9:
						System.out.print("Enter new credit card number : ");
						long credit_no = Input.GetLong();
						guest.setCreditNum(credit_no);
						break;
					case 0:
						break;
					default:
						System.out.println("Invalid option!");
						break;
					}
				} while (choice != 0);
				guestDao.updateGuest(Identity, guest);
			}
		}
	}

	/**
	 * Prints guest by identity
	 * 
	 * @param Identity
	 *            The guest identity
	 */
	public void searchGuestbyId(String Identity) {
		guestDao.searchGuestbyId(Identity);
	}

	/**
	 * Prints guest by name
	 * 
	 * @param name
	 *            The guest name
	 */
	public void searchGuestbyName(String name) {
		guestDao.searchGuestbyName(name);
	}

	/**
	 * Write guest data
	 */
	public void WritetoFile() {
		guestDao.updateFile();
	}

	/**
	 * Gets guest DAO
	 * 
	 * @return
	 */
	public GuestDao getGuestDao() {
		return (GuestDaoImpl) guestDao;
	}
}