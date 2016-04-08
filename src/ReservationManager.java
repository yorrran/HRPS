import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReservationManager
{
    private ReservationDao reservationDao;
    private RoomManager roomManager;
    private GuestManager guestManager;
    private DecimalFormat twoint = new DecimalFormat("00");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private ArrayList<Room> availableRoomList = null;
    
    public ReservationManager(RoomManager roomManager,GuestManager guestManager)
    {
        reservationDao = new ReservationDaoImpl();
        this.roomManager = roomManager;
        this.guestManager = guestManager;
    }

    public void addReservation()
    {
    	Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date reservationDatetime = digiClock.getTime();//get reservation datetime
    	
        System.out.print("Enter your passport or driving license: ");
        String identity = null;
        identity = Input.GetString();
        
        
        
        Room room = null;
        String reservationCode = new StringBuilder().append(getCurrentDateTime()).append(identity).toString();
        //get reservation code
        System.out.print("Enter number of adults: ");
        int numberOfAdults = Input.GetInt();

        System.out.print("Enter number of children: ");
        int numberOfChildren = Input.GetInt();
        
        Date checkinDate = null;
        Date tempdate = null;
        Date checkOutDate = null;
        do
        {
        System.out.print("Enter check in date(dd/MM/yyyy): ");
        String checkInString = Input.GetString();
       
        try
        {
            checkinDate = dateFormat.parse(checkInString);
            tempdate = dateFormat.parse(checkInString);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }//get check in date

        System.out.print("Enter check out date(dd/MM/yyyy): ");
        String checkOutString = Input.GetString();
        
        try
        {
            checkOutDate = dateFormat.parse(checkOutString);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }//get check out date
        if(checkinDate.compareTo(checkOutDate)>=0)
        	System.out.println("Check in date must be eaelier than check out date");
        }while(checkinDate.compareTo(checkOutDate)>=0);
        
        availableRoomList = new ArrayList<Room>(roomManager.getAllRoom());//make a copy of room list
        this.updateAvailableRoomList(availableRoomList, tempdate, checkOutDate);
        long diff = checkOutDate.getTime() - checkinDate.getTime();
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));//get difference of days between check out and check in
        

        //System.out.println(roomManager.getAllRoom().size()+" "+availableRoomList.size());
        this.displayAvailableRoom(availableRoomList);//display the available room within the checkin and checkout date
        System.out.println("Continue to make reservation?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("3. Add wait List");
        int choice  = Input.GetInt();
        while (choice < 1 || choice > 3)
        {
            System.out.print("Please enter correct choice: ");
            choice = Input.GetInt();
        }
        System.out.println("");
        
        switch(choice)
        {
        	case 1:
        		 do
        	        {
        	            System.out.print("Enter the room number: ");
        	            int roomNum = Input.GetInt(); 
        	            boolean foundAvailableRoom =false;
        	            room = this.getRoomByRoomNum(roomNum);
        	            	 for(int i=0;i<availableRoomList.size();i++)
        	                 {
        	                 	if(room.roomNumber == availableRoomList.get(i).getRoomNumber())
        	                 	{
        	                 		foundAvailableRoom = true;
        	                 		break;
        	                 	}
        	                 }   
        	                 if(foundAvailableRoom)
        	                 {
        	     	            if (room.getRoomStatus().equals(RoomStatus.returnStatus(4)))//not allow to book if room is under maintain
        	     	            {
        	     	                System.out.println("This room is unavailable now, please select another room.");
        	     	                room = null;
        	     	            }
        	                  }
        	                  else
        	                 {	
        	                 	System.out.println("The room is unavailable in that period, please select another room.");
        	                 	room = null;
        	                 }          
        	        } while (room == null);//selecte the vacant room

        		 	double price = numOfDays * (room.getRoomType().getPrice() + room.getFacing().getPrice());//get price for room type and facing type

        	        Reservation reservation = new Reservation(reservationCode, identity, room, price, reservationDatetime, checkinDate, checkOutDate, numberOfAdults, numberOfChildren, ReservationStatus.returnStatus(1));
        	        reservationDao.addReservation(reservation);
        	break;
        	
        	case 2:
        		
        	break;
        	
        	case 3:
        		roomManager.displayRoomType();
        		System.out.print("Select the room type to make wait list: ");
        		int choiceOftype = Input.GetInt();
        		while(choiceOftype<1 || choiceOftype>roomManager.getRoomDao().getAllRoomType().size())
        		{
        			System.out.print("Please select correct room type to make wait list: ");
            		choiceOftype = Input.GetInt();
        		}
        		boolean roomUnavailable = false;
        		for(int i = 0;i<availableRoomList.size(); i++)
        		{
        			if(availableRoomList.get(i).getRoomType().getType().equals(roomManager.getRoomDao().getAllRoomType().get(choiceOftype-1).getType())&&!availableRoomList.get(i).getRoomStatus().equals(RoomStatus.returnStatus(4)))
        			{
        				System.out.println("The "+roomManager.getRoomDao().getAllRoomType().get(choiceOftype-1).getType() +" room type is available, please do reservation");
        				roomUnavailable = true;
        				break;
        			}	
        		}
        		if(!roomUnavailable)
        		{   
        			for(int i=0;i<roomManager.getAllRoom().size();i++)
        			{
        				if(roomManager.getRoomDao().getAllRoomType().get(choiceOftype-1).getType().equals(roomManager.getAllRoom().get(i).getRoomType().getType()))
        				{
        					room = roomManager.getAllRoom().get(i);
        					break;
        				}
        			}
        			double priceForWaitList = numOfDays * (room.getRoomType().getPrice() + room.getFacing().getPrice());//get price for room type and facing type
        	        Reservation waitList = new Reservation(reservationCode, identity, room, priceForWaitList, reservationDatetime, checkinDate, checkOutDate, numberOfAdults, numberOfChildren, ReservationStatus.returnStatus(2));
        	        reservationDao.addReservation(waitList);
        		}
        	break;
        }
        
       
        //this.updateStatusBySystem(room, RoomStatus.returnStatus(3));//update room status to reserved
        //String reportDate = dateFormat.format(checkinDate);
    }

    public void updateReservation()
    {
        System.out.print("Enter the reservation code to update reservation: ");
        String reservationCode = Input.GetString();
        Reservation reservation = reservationDao.searchReservationByReservationCode(reservationCode);
        if (reservation.getReservationStatus().equals(ReservationStatus.returnStatus(4)))
        {
            System.out.println("Reservation is expired, update is not allow");
        }
        else
        {
            System.out.println("Reservation Information ");
            System.out.println("1. Cancle the reservation");
            System.out.println("2. Number of adults");
            System.out.println("3. Number of children");
            System.out.println("4. Cancel update");
            System.out.print("Select which detail you want to update: ");
            int choice = Input.GetInt();
            while (choice < 1 || choice > 6)
            {
                System.out.print("Please enter correct choice: ");
                choice = Input.GetInt();
            }
            System.out.println("");

            long diff;
            int numOfDays;
            double price;
            Calendar digiClock = Calendar.getInstance();
            digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
            Date reservationDatetime = digiClock.getTime();//get reservation datetime
            switch (choice)
            {
                case 1:
                	for(int i = 0; i < reservationDao.getAllReservation().size(); i++)
                	{
                		if(reservationCode.equals(reservationDao.getAllReservation().get(i).getReservationCode()))
                		{
                			reservationDao.getAllReservation().get(i).setReservationStatus(ReservationStatus.returnStatus(4));
                			break;
                		}
                	}
                    break;
               
                case 2:
                    System.out.print("Enter number of adults: ");
                    int numberOfAdults = Input.GetInt();
                    reservation.setReservationDatetime(reservationDatetime);
                    reservation.setNumOfAdult(numberOfAdults);
                    break;
                case 3:
                    System.out.print("Enter number of children: ");
                    int numberOfChildren = Input.GetInt();
                    reservation.setReservationDatetime(reservationDatetime);
                    reservation.setNumOfAdult(numberOfChildren);
                    break;
                default:
                    System.out.println("Update canceled.");


            }
        }
    }

    public void displayAllReservation()
    {
        for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
        {
            String reservationCode = reservationDao.getAllReservation().get(i).getReservationCode();
            String identity = reservationDao.getAllReservation().get(i).getIdentity();
            Room room = reservationDao.getAllReservation().get(i).getRoom();
            double price = reservationDao.getAllReservation().get(i).getPrice();
            Date reservationDatetime = reservationDao.getAllReservation().get(i).getReservationDatetime();
            Date checkInDatetime = reservationDao.getAllReservation().get(i).getCheckInDatetime();
            Date checkOutDatetime = reservationDao.getAllReservation().get(i).getCheckOutDatetime();
            int numOfAdult = reservationDao.getAllReservation().get(i).getNumOfAdult();
            int numOfChildren = reservationDao.getAllReservation().get(i).getNumOfChildren();
            ReservationStatus reservationStatus = reservationDao.getAllReservation().get(i).getReservationStatus();

            System.out.println("Reservation Code: " + reservationCode + ", Identity: " + identity + ", Reservation Date: " + dateFormat2.format(reservationDatetime));
            System.out.println("Room number: " + room.getRoomNumber() + ", Price: " + price + ", Check In Date: " + dateFormat.format(checkInDatetime) + ", Check Out Date: " + dateFormat.format(checkOutDatetime));
            System.out.println("Number of adult: " + numOfAdult + ", number of children: " + numOfChildren);
            System.out.println("Reservation Status: " + reservationStatus.toString());
            System.out.println("---------------------------------------");
        }
    }

    public void displayReservationByCode(String reservationCode)
    {
        Reservation reservation = reservationDao.searchReservationByReservationCode(reservationCode);
        String identity = reservation.getIdentity();
        Room room = reservation.getRoom();
        double price = reservation.getPrice();
        Date reservationDatetime = reservation.getReservationDatetime();
        Date checkInDatetime = reservation.getCheckInDatetime();
        Date checkOutDatetime = reservation.getCheckOutDatetime();
        int numOfAdult = reservation.getNumOfAdult();
        int numOfChildren = reservation.getNumOfChildren();
        ReservationStatus reservationStatus = reservation.getReservationStatus();

        System.out.println("Reservation Code: " + reservationCode + ", Identity: " + identity + ", Reservation Date: " + dateFormat2.format(reservationDatetime));
        System.out.println("Room number: " + room.getRoomNumber() + ", Price: " + price + ", Check In Date: " + dateFormat.format(checkInDatetime) + ", Check Out Date: " + dateFormat.format(checkOutDatetime));
        System.out.println("Number of adult: " + numOfAdult + ", number of children: " + numOfChildren);
        System.out.println("Reservation Status: " + reservationStatus.toString());
        System.out.println("---------------------------------------");
    }

    public void removeReservation()
    {
        System.out.print("Enter the reservation code to remove reservation: ");
        String reservationCode = Input.GetString();
        Reservation reservation = reservationDao.searchReservationByReservationCode(reservationCode);
        if (reservation.getReservationStatus().equals(ReservationStatus.returnStatus(4)))
        {
            reservationDao.removeReservation(reservation);//only allow to remove when the status is expired
        }
        else
        {
            System.out.println("This reservation cannot be removed in this moment");
        }
    }

    private String getCurrentDateTime()
    {
        Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        int hour = digiClock.get(Calendar.HOUR_OF_DAY);
        int min = digiClock.get(Calendar.MINUTE);
        int sec = digiClock.get(Calendar.SATURDAY);
        int day = digiClock.get(Calendar.DAY_OF_MONTH);
        int month = digiClock.get(Calendar.MONTH) + 1;
        int year = digiClock.get(Calendar.YEAR);
        String currentDateTime = new StringBuilder().append(year).append(twoint.format(month)).append(twoint.format(day)).append(twoint.format(hour))
                .append(twoint.format(min)).append(twoint.format(sec)).toString();

        return currentDateTime;
    }

    public Room getRoomByRoomNum(int roomNum)
    {
        return roomManager.getRoomDao().getRoomByRoomNum(roomNum);
    }

    //make reservation expired if user not check in after 3pm of the check in date
    public void makeReservationExpired()
    {
        Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date current = digiClock.getTime();
        digiClock.set(Calendar.HOUR_OF_DAY,15);
        digiClock.set(Calendar.MINUTE,0);
        digiClock.set(Calendar.SECOND,0);
        Date tempCheckInDate = new Date();
        for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
        {
        	tempCheckInDate = reservationDao.getAllReservation().get(i).getCheckInDatetime();
        	tempCheckInDate = digiClock.getTime();
            if (current.compareTo(tempCheckInDate) >= 0 &&
                    !reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(4)))
            {
                reservationDao.getAllReservation().get(i).setReservationStatus(ReservationStatus.returnStatus(4));
                //Room room = this.getRoomByRoomNum(reservationDao.getAllReservation().get(i).getRoom().getRoomNumber());
                //this.updateStatusBySystem(room, RoomStatus.returnStatus(1));
            }
        }
    }
    
    public void updateStatusBySystem(Room room, RoomStatus roomStatus)
    {
        room.setRoomStatus(roomStatus);
    }
    
    public void displayAvailableRoom(ArrayList<Room> availableRoomList)
    {
    	int noOfVacant = 0, total = 0;
        System.out.println("\nThe following room are available to reserve");
        for (int i = 0; i < roomManager.getRoomDao().getAllRoomType().size(); i++)
        {
            for (int j = 0; j < roomManager.getAllRoom().size(); j++)
            {
                if (roomManager.getRoomDao().getAllRoomType().get(i).getType().equals(roomManager.getRoomDao().getAllRoom().get(j).getRoomType().getType()))
                {
                    total++;
                }
            }
            for (int j = 0; j < availableRoomList.size(); j++)
             {
                if (roomManager.getRoomDao().getAllRoomType().get(i).getType().equals(availableRoomList.get(j).getRoomType().getType()) && !availableRoomList.get(j).getRoomStatus().equals(RoomStatus.returnStatus(4)))
                {
                    noOfVacant++;
                }
            }
            System.out.println(roomManager.getRoomDao().getAllRoomType().get(i).getType() + ": Number: " + noOfVacant + " out of " + total);
            System.out.print("Rooms: ");
            for (int k = 0; k < availableRoomList.size(); k++)
            {
                if (roomManager.getRoomDao().getAllRoomType().get(i).getType().equals(availableRoomList.get(k).getRoomType().getType()) && !availableRoomList.get(k).getRoomStatus().equals(RoomStatus.returnStatus(4)))
                    System.out.print("0" + availableRoomList.get(k).getRoomNumber() + ", ");
            }
            System.out.println("");
            noOfVacant = 0;
            total = 0;
        }
    }
    
    public void updateAvailableRoomList(ArrayList<Room> availableRoomList, Date checkInDate,Date checkOutDate)
    {
    	while(checkInDate.compareTo(checkOutDate)<=0)
        {	
          for(int i =0;i<reservationDao.getAllReservation().size();i++)
          {
        	  if(checkInDate.compareTo(reservationDao.getAllReservation().get(i).getCheckInDatetime())>=0 && checkInDate.compareTo(reservationDao.getAllReservation().get(i).getCheckOutDatetime())<=0 && !reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(4)))
        	  {
        		  for(int j=0;j<availableRoomList.size();j++)
        		  {
        			  if(availableRoomList.get(j).getRoomNumber()==reservationDao.getAllReservation().get(i).getRoom().getRoomNumber())
        			  {
        				  availableRoomList.remove(j);
        			  }
        		  }  
        	  }
          }
        	long time = checkInDate.getTime();
            time += 1000 * 60 * 60 * 24;
            checkInDate.setTime(time); 
        }//remove the unavailable room within the checkin and checkout date
    }

    public void updateWaitList()
    {
    	ArrayList<Reservation> waitList=new ArrayList<Reservation>();
    	for(int i = 0; i <reservationDao.getAllReservation().size();i++)
    	{
    		if(reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(2)))
    		{
    			waitList.add(reservationDao.getAllReservation().get(i));
    		}
    	}
    	availableRoomList = new ArrayList<Room>(roomManager.getAllRoom());
    	Date checkInDatetime = new Date();
    	String strCheckIn = null;
    	for(int i = 0;i<waitList.size();i++)
    	{
    		strCheckIn =  dateFormat.format(waitList.get(i).getCheckInDatetime());
    		try {
				checkInDatetime = dateFormat.parse(strCheckIn);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            Date checkOutDatetime = waitList.get(i).getCheckOutDatetime(); 
            this.updateAvailableRoomList(availableRoomList, checkInDatetime, checkOutDatetime);
            
            
            for(int k = 0; k < availableRoomList.size();k++)
            {
            	if(availableRoomList.get(k).getRoomType().getType().equals(waitList.get(i).getRoom().getRoomType().getType()) &&!availableRoomList.get(k).getRoomStatus().equals(RoomStatus.returnStatus(4)))
            	{
            		Room room = null;
            		for(int j=0;j<roomManager.getAllRoom().size();j++)
            		{
            			if(availableRoomList.get(k).getRoomNumber()==roomManager.getAllRoom().get(j).getRoomNumber())
            			{
            				room = roomManager.getAllRoom().get(j);
            			}
            		}
            		waitList.get(i).setRoom(room);
            		waitList.get(i).setReservationStatus(ReservationStatus.returnStatus(1));
            	}
            }
    	}
    	System.out.println(""+availableRoomList.size());
    }
    
    public void WritetoFile()
    {
        reservationDao.updateFile();
    }
    
    public ReservationDaoImpl getReservationDao() { return (ReservationDaoImpl)reservationDao; }
}