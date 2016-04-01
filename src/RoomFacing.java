import java.io.Serializable;

public class RoomFacing implements Serializable{
	
	private int facingId;
	private String facingType;
	private double price;

	public RoomFacing(int id, String type, double price)
	{
		this.facingId = id;
		this.facingType = type;
		this.price = price;
	}
	
	public int getFacingId() {
		return facingId;
	}

	public void setFacingId(int facingId) {
		this.facingId = facingId;
	}

	public String getFacingType() {
		return facingType;
	}

	public void setFacingType(String facingType) {
		this.facingType = facingType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
