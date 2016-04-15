public enum RoomBedType
{
	/**
	 * The single bed.
	 */
    Single, 
    
    /**
     * The double bed.
     */
    Double, 
    
    /**
     * The master bed.
     */
    Master;
    
    /**
     * Prints the room bed type. 
     */
    public static void printRoomBedType()
    {
        int i = 1;
        for (RoomBedType bedType : RoomBedType.values())
        {
            System.out.println(i + ". " + bedType);
            i++;
        }
    }

    /**
     * Gets room bed type by bed type number.
     * @param bedTypeNum The bed type number.
     * @return the room bed type.
     */
    public static RoomBedType returnRoomBedType(int bedTypeNum)
    {
        switch (bedTypeNum)
        {
            case 1:
                return Single;
            case 2:
                return Double;
            case 3:
                return Master;
            default:
                return null;
        }
    }
}