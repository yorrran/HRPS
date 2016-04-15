
public enum ReservationStatus
{
	/**
	 * The confirmed status.
	 */
    Confirmed, 
    /**
     * The waiting list status.
     */
    WaitingList, 
    /**
     * The checked in status.
     */
    CheckedIn, 
    /**
     * The expired status.
     */
    Expired;

    /**
     * Gets the reservation status by status number.
     * @param statusNumber The status number.
     * @return the reservation status.
     */
    public static ReservationStatus returnStatus(int statusNumber)
    {
        switch (statusNumber)
        {
            case 1:
                return Confirmed;
            case 2:
                return WaitingList;
            case 3:
                return CheckedIn;
            case 4:
                return Expired;
            default:
                return null;
        }
    }
}