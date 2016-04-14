public enum RoomStatus
{
    Vacant, Occupied, Reserved, UnderMaintenance;

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