
public enum ReservationStatus {

	Confirmed, InWaitlist, CheckedIn, Expired;
	
	
	public static void printReservationStatus()
	{
		int i = 1;
		for(ReservationStatus status : ReservationStatus.values())
			{
				System.out.println(i+". "+status);
				i++;
			}
	}
	
	public static ReservationStatus returnStatus(int statusNumber)
	{
		switch(statusNumber)
		{
			case 1:
				return Confirmed;
			
			case 2:
				return InWaitlist;
			
			case 3:
				return CheckedIn;
			
			case 4:
				return Expired;
			
			default:
				return null;
			
		}
	}
}
