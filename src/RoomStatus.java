public enum RoomStatus
{
    Vacant, Occupied, Reserved, UnderMaintenance;

    public static void printRoomStatus()
    {
        int i = 1;
        for (RoomStatus status : RoomStatus.values())
        {
            System.out.println(i + ". " + status);
            i++;
        }
    }

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