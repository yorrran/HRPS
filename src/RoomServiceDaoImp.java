import java.util.ArrayList;

/**
 * Room service data access object.
 */
public class RoomServiceDaoImp implements RoomServiceDao {
	private static final String fileName = "File/RoomService.dat";
	private static ArrayList<RoomService> roomServiceList = new ArrayList<RoomService>();

	/**
	 * Class constructor. Room service is loaded from data file.
	 */
	public RoomServiceDaoImp() {
		roomServiceList = DataIO.Read(fileName);
	}

	/**
	 * Add a new room service to the existing list.
	 * 
	 * @param roomService
	 *            The new room service.
	 */
	@Override
	public void addRoomService(RoomService roomService) {
		roomServiceList.add(roomService);
		System.out.println("Room Service Added Successfully! ");
	}

	/**
	 * Update a room service.
	 * 
	 * @param index
	 *            The index of room service to be updated.
	 * @param roomService
	 *            The room service to be updated.
	 */
	@Override
	public void updateRoomService(int index, RoomService roomService) {
		roomServiceList.get(index).setService(roomService.getService());
		roomServiceList.get(index).setPrice(roomService.getPrice());
		System.out.println("Room Service updated successfully!");
	}

	/**
	 * Remove a room service.
	 * 
	 * @param roomService
	 *            The room service to be removed.
	 */
	@Override
	public void removeRoomService(RoomService roomService) {
		roomServiceList.remove(roomService);
		System.out.println("Room service removed!");
	}

	/**
	 * Display all room service available.
	 */
	@Override
	public void displayRoomService() {
		System.out.println("---------- Room Service List ----------");

		for (int i = 0; i < roomServiceList.size(); i++) {
			System.out.println((i + 1) + ". "
					+ roomServiceList.get(i).getService() + "\t$"
					+ roomServiceList.get(i).getPrice());
		}

		System.out.println("------------------------------");
	}

	/**
	 * Returns the list of room services so that manager class can access it.
	 * 
	 * @return the list of room services.
	 */
	@Override
	public ArrayList<RoomService> getAllRoomService() {
		return roomServiceList;
	}

	/**
	 * Write the current list data to file.
	 */
	@Override
	public void updateFile() {
		DataIO.Write(fileName, roomServiceList);
	}
}