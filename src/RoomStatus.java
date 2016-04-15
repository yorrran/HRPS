public enum RoomStatus
{	
	/**
	 * The vacant status.
	 */
    Vacant,
    /**
     * The occupied status.
     */
    Occupied,
    /**
     * The reserved status.
     */
    Reserved,
    /**
     * The under maintenance status.
     */
    UnderMaintenance;
    
    /**
     * Gets room status by status number.
     * @param statusNumber the number of status.
     * @return the name of status.
     */
    public static RoomStatus returnStatus(int statusNumber)
    {
        switch (statusNumber)
        {
            case 1:
                return Vacant;
            case 2:
                return Occupied;
            case 3:
                return Reserved;
            case 4:
                return UnderMaintenance;
            default:
                return null;
        }
    }
}