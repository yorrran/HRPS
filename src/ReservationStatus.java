
public enum ReservationStatus
{
    Confirmed, WaitingList, CheckedIn, Expired;

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