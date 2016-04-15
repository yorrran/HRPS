import java.io.Serializable;

/**
 * RoomService is a class that stores all the details of the room service
 * provided.
 */
public class RoomService implements Serializable {
	/**
	 * The id of room service.
	 */
	private int id;

	/**
	 * The name of the service.
	 */
	private String service;

	/**
	 * The price of room service.
	 */
	private double price;

	/**
	 * Class constructor specifying the room service name and its price.
	 * 
	 * @param id
	 *            The id of the room service.
	 * @param service
	 *            The name of the room service.
	 * @param price
	 *            The price of the room service.
	 */
	public RoomService(int id, String service, double price) {
		this.id = id;
		this.service = service;
		this.price = price;
	}

	/**
	 * Gets the name of the service.
	 * 
	 * @return the name of the room service.
	 */
	public String getService() {
		return service;
	}

	/**
	 * Change the name of the room service.
	 * 
	 * @param service
	 *            The new name of the room service.
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * Gets the price of the room service.
	 * 
	 * @return the price of the room service.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Change the price of the room service.
	 * 
	 * @param price
	 *            The new price of the room service.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
