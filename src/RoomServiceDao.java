import java.util.ArrayList;

/**
 * Interface for room service data access object.
 */
public interface RoomServiceDao {
	/**
	 * Gets the room service list.
	 * 
	 * @return the room service list.
	 */
	ArrayList<RoomService> getAllRoomService();

	/**
	 * Add a new room service to the existing list.
	 * 
	 * @param roomService
	 *            The new room service.
	 */
	void addRoomService(RoomService rst);

	/**
	 * Update a room service.
	 * 
	 * @param index
	 *            The index of room service to be updated.
	 * @param roomService
	 *            The room service to be updated.
	 */
	void updateRoomService(int index, RoomService rst);

	/**
	 * Remove a room service.
	 * 
	 * @param roomService
	 *            The room service to be removed.
	 */
	void removeRoomService(RoomService rst);

	/**
	 * Display all room service available.
	 */
	void displayRoomService();

	/**
	 * Write the current list data to file.
	 */
	void updateFile();
}