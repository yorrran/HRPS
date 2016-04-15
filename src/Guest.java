import java.io.Serializable;

/**
 * Guest is a class that stores all the details of the guest information.
 */
public class Guest implements Serializable {
	private String identity;
	private String name;
	private String country;
	private String gender;
	private String address;
	private String nationality;
	private int phoneNum;
	private String email;
	private long creditNum;

	/**
	 * Class constructor specifying the guest it is attached to based on guest
	 * identity.
	 * 
	 * @param identity
	 *            Specifies guest information for individual guest.
	 * @param name
	 *            specifies guest's name
	 * @param country
	 *            specifies guest's country
	 * @param gender
	 *            specifies guest's gender
	 * @param address
	 *            specifies guest's address
	 * @param nationality
	 *            specifies guest's nationality
	 * @param phoneNum
	 *            specifies guest's contact number
	 * @param email
	 *            specifies guest's email
	 * @param credit
	 *            specifies guest's credit card number
	 */

	public Guest(String identity, String name, String country, String gender,
			String address, String nationality, int phoneNum, String email,
			long creditNum) {
		this.identity = identity;
		this.name = name;
		this.country = country;
		this.gender = gender;
		this.address = address;
		this.nationality = nationality;
		this.phoneNum = phoneNum;
		this.email = email;
		this.creditNum = creditNum;
	}

	/**
	 * Get the identification number of the guest
	 * 
	 * @return this guest identity.
	 */

	public String getIdentity() {
		return identity;
	}

	/**
	 * Change the identification number of the guest
	 * 
	 * @param identity
	 *            This guest new identity
	 */

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * Get the name of the guest
	 * 
	 * @return this guest identity
	 */

	public String getName() {
		return name;
	}

	/**
	 * Change the name of the guest
	 * 
	 * @param name
	 *            This guest new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the country of the guest
	 * 
	 * @return this guest country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Change the country of the guest
	 * 
	 * @param name
	 *            This guest's new country
	 */

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Get the gender of the guest
	 * 
	 * @return this guest's gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Change the gender of the guest
	 * 
	 * @param name
	 *            This guest's new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Get the Address of the guest
	 * 
	 * @return this guest's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Change the address of the guest
	 * 
	 * @param name
	 *            This guest's new address
	 */

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the nationality of the guest
	 * 
	 * @return this guest's nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Change the nationality of the guest
	 * 
	 * @param name
	 *            This guest's new nationality
	 */

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Get the phonenum of the guest
	 * 
	 * @return this guest's phonenum
	 */
	public int getPhoneNum() {
		return phoneNum;
	}

	/**
	 * Change the phonenum of the guest
	 * 
	 * @param name
	 *            This guest's phonenum
	 */
	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * Get the Email of the guest
	 * 
	 * @return this guest's Email
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Change the Email of the guest
	 * 
	 * @param name
	 *            This guest's new Email
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the CreditNum of the guest
	 * 
	 * @return this guest's new CreditNum
	 */

	public long getCreditNum() {
		return creditNum;
	}

	/**
	 * Change the CreditNum of the guest
	 * 
	 * @param name
	 *            This guest's new CreditNum
	 */

	public void setCreditNum(long credit_no) {
		this.creditNum = creditNum;
	}
}