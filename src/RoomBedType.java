
public enum RoomBedType {
	Single, Double, Master;
	
	public static void printRoomBedType()
	{
		int i = 1;
		for(RoomBedType bedType : RoomBedType.values())
			{
				System.out.println(i+". "+bedType);
				i++;
			}
	}
	
	public static RoomBedType returnRoomBedType(int bedTypeNum)
	{
		switch(bedTypeNum)
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
