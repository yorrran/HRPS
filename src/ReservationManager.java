import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReservationManager
{	
	/**
	 * The reservation DAO.
	 */
    private ReservationDao reservationDao;
    /**
     * The room manager.
     */
    private RoomManager roomManager;
    /**
     * The guest manager.
     */
    private GuestManager guestManager;
    /**
     * The decimal format.
     */
    private DecimalFormat twoint = new DecimalFormat("00");
    /**
     * The date format.
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * The date format.
     */
    private SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    /**
     * The avaiavle room list.
     */
    private ArrayList<Room> availableRoomList = null;
    
    /**
     * Creates the reservation manager.
     * @param roomManager The room manager.
     * @param guestManager The guest manager.
     */
    public ReservationManager(RoomManager roomManager, GuestManager guestManager)
    {
        reservationDao = new ReservationDaoImpl();
        this.roomManager = roomManager;
        this.guestManager = guestManager;
        updateRoomStatusByReservationList();
        makeReservationExpired();
        updateWaitList();
    }
    
    /**
     * Email to guest as reminder of check in.
     */
    public void emailGuest()
    {
        Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date current = digiClock.getTime();

        for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
        {
            if (reservationDao.getAllReservation().get(i).getCheckInDatetime().getDay() == current.getDay() &&
                    reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(1)))
            {
                for (int j = 0; j < guestManager.getGuestDao().getAllGuest().size(); j++)
                {
                    if (guestManager.getGuestDao().getAllGuest().get(j).getIdentity() == reservationDao.getAllReservation().get(i).getIdentity())
                    {
                        String subject = "Reminder of Check-in";
                        String body = "Your reservation is today. Please check-in before 3pm otherwise your reservation will be expired.\n\n" +
                                "Have a nice day and we hope you will enjoy your stay at our hotel!\n\nWarm regards,\nHRPS Hotel";

                        Notification.Email(guestManager.getGuestDao().getAllGuest().get(i).getEmail(), subject, body);
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Creates reservation.
     */
    public void addReservation()
    {
        makeReservationExpired();

        Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date reservationDatetime = digiClock.getTime();//get reservation datetime

        System.out.print("Enter your passport or driving license: ");
        String identity = null;
        identity = Input.GetString();
        boolean flag = false;

        for (int i = 0; i < guestManager.getGuestDao().getAllGuest().size(); i++)
        {
            if (guestManager.getGuestDao().getAllGuest().get(i).getIdentity().equals(identity))
            {
                System.out.println("Customer already exists");
                flag = true;
                break;
            }

        }

        if (!flag)
        {
            System.out.println("This customer is a new customer");
            guestManager.addGuest();
        }


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
            //checkInString = new StringBuilder().append(" 15:mm:ss");
            try
            {
                checkinDate = dateFormat.parse(checkInString);
                tempdate = dateFormat.parse(checkInString);
            } catch (ParseException e)
            {
                e.printStackTrace();
            }//get check in date

            System.out.print("Enter check out date(dd/MM/yyyy): ");
            String checkOutString = new StringBuilder().append(Input.GetString()).append(" 15:00:00").toString();

            try
            {
                checkOutDate = dateFormat.parse(checkOutString);
            } catch (ParseException e)
            {
                e.printStackTrace();
            }//get check out date

            if (checkinDate.compareTo(checkOutDate) >= 0)
                System.out.println("Check in date must be eaelier than check out date");
        } while (checkinDate.compareTo(checkOutDate) >= 0);

        availableRoomList = new ArrayList<Room>(roomManager.getAllRoom());//make a copy of room list
        this.updateAvailableRoomList(availableRoomList, tempdate, checkOutDate);

        this.displayAvailableRoom(availableRoomList);//display the available room within the checkin and checkout date
        System.out.println("Continue to make reservation?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("3. Add wait List");
        int choice = Input.GetInt();
        while (choice < 1 || choice > 3)
        {
            System.out.print("Please enter correct choice: ");
            choice = Input.GetInt();
        }
        System.out.println("");

        switch (choice)
        {
            case 1:
                do
                {
                    System.out.print("Enter the room number: ");
                    int roomNum = Input.GetInt();
                    boolean foundAvailableRoom = false;
                    room = roomManager.getRoomByRoomNum(roomNum);
                    for (int i = 0; i < availableRoomList.size(); i++)
                    {
                        if (room.getRoomNumber() == availableRoomList.get(i).getRoomNumber())
                        {
                            foundAvailableRoom = true;
                            break;
                        }
                    }
                    if (foundAvailableRoom)
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
                } while (room == null);//select the vacant room

                double price = room.getRoomType().getPrice() + room.getFacing().getPrice();
                Reservation reservation = new Reservation(reservationCode, identity, room, price, reservationDatetime, checkinDate, checkOutDate, numberOfAdults, numberOfChildren, ReservationStatus.returnStatus(1));
                reservationDao.addReservation(reservation);
                break;

            case 2:

                break;

            case 3:
                roomManager.displayRoomType();
                System.out.print("Select the room type to make wait list: ");
                int choiceOftype = Input.GetInt();
                while (choiceOftype < 1 || choiceOftype > roomManager.getRoomDao().getAllRoomType().size())
                {
                    System.out.print("Please select correct room type to make wait list: ");
                    choiceOftype = Input.GetInt();
                }
                boolean roomUnavailable = false;
                for (int i = 0; i < availableRoomList.size(); i++)
                {
                    if (availableRoomList.get(i).getRoomType().getType().equals(roomManager.getRoomDao().getAllRoomType().get(choiceOftype - 1).getType()) && !availableRoomList.get(i).getRoomStatus().equals(RoomStatus.returnStatus(4)))
                    {
                        System.out.println("The " + roomManager.getRoomDao().getAllRoomType().get(choiceOftype - 1).getType() + " room type is available, please do reservation");
                        roomUnavailable = true;
                        break;
                    }
                }
                if (!roomUnavailable)
                {
                    for (int i = 0; i < roomManager.getAllRoom().size(); i++)
                    {
                        if (roomManager.getRoomDao().getAllRoomType().get(choiceOftype - 1).getType().equals(roomManager.getAllRoom().get(i).getRoomType().getType()))
                        {
                            room = roomManager.getAllRoom().get(i);
                            break;
                        }
                    }
                    double priceForWaitList = room.getRoomType().getPrice() + room.getFacing().getPrice();//get price for room type and facing type
                    Reservation waitList = new Reservation(reservationCode, identity, room, priceForWaitList, reservationDatetime, checkinDate, checkOutDate, numberOfAdults, numberOfChildren, ReservationStatus.returnStatus(2));
                    reservationDao.addReservation(waitList);
                }
                break;
        }

        updateRoomStatusByReservationList();
    }
    
    /**
     * Gets specific reservation by reservation code.
     * @param reservationCode The reservation code.
     * @return Gets the reservation.
     */
    public Reservation searchReservationbyReservationCode(String reservationCode)
    {
        return reservationDao.searchReservationByReservationCode(reservationCode);
    }

    /**
     * Updates reservation.
     */
    public void updateReservation()
    {
        System.out.print("Enter the reservation code to update reservation: ");
        String reservationCode = Input.GetString();
        Reservation reservation = reservationDao.searchReservationByReservationCode(reservationCode);

        if (reservation != null)
        {
            if (reservation.getReservationStatus().equals(ReservationStatus.returnStatus(4)))
            {
                System.out.println("Reservation is expired, update is not allow");
            }
            else
            {
                System.out.println("Reservation Information ");
                System.out.println("1. Cancel the reservation");
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
                        for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
                        {
                            if (reservationCode.equals(reservationDao.getAllReservation().get(i).getReservationCode()))
                            {
                                reservationDao.getAllReservation().get(i).setReservationStatus(ReservationStatus.returnStatus(4));
                                updateWaitList();
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
                        reservation.setNumOfChildren(numberOfChildren);
                        break;
                    default:
                        System.out.println("Update canceled.");
                }
            }
        }
    }
    
    /**
     * Prints all reservation details.
     */
    public void displayAllReservation()
    {
        makeReservationExpired();

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
    
    /**
     * Prints specific reservation.
     */
    public void displayReservationByCode()
    {
        makeReservationExpired();

        System.out.print("Enter reservation code or 0 to cancel: ");
        String reservationCode = Input.GetString();

        if (!reservationCode.equals("0"))
        {
            Reservation reservation = reservationDao.searchReservationByReservationCode(reservationCode);

            if (reservation != null)
            {
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
        }
    }

    /**
     * Remove the reservation.
     */
    public void removeReservation()
    {
        makeReservationExpired();

        System.out.print("Enter the reservation code to remove reservation: ");
        String reservationCode = Input.GetString();
        Reservation reservation = reservationDao.searchReservationByReservationCode(reservationCode);

        if (reservation != null)
        {
            if (reservation.getReservationStatus().equals(ReservationStatus.returnStatus(4)))
            {
                reservationDao.removeReservation(reservation);//only allow to remove when the status is expired
            }
            else
            {
                System.out.println("This reservation cannot be removed in this moment");
            }
        }
    }
    
    /**
     * Gets the current time in string.
     * @return The current time in string.
     */
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
    
    /**
     * Makes reservation expired if user not check in after 3pm of the check in date.
     */
    @SuppressWarnings("deprecation")
    public void makeReservationExpired()
    {
        Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date current = digiClock.getTime();
        digiClock.set(Calendar.HOUR_OF_DAY, 15);
        digiClock.set(Calendar.MINUTE, 0);
        digiClock.set(Calendar.SECOND, 0);

        Date tempCheckInDate = new Date();
        String strCheckIn = null;
        for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
        {
            strCheckIn = dateFormat.format(reservationDao.getAllReservation().get(i).getCheckInDatetime());
            try
            {
                tempCheckInDate = dateFormat.parse(strCheckIn);
                tempCheckInDate.setHours(digiClock.getTime().getHours());
            } catch (ParseException e)
            {
                e.printStackTrace();
            }

            if (current.compareTo(tempCheckInDate) >= 0 && !reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(4)) && !reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(3)))
            {
                reservationDao.getAllReservation().get(i).setReservationStatus(ReservationStatus.returnStatus(4));
            }
        }
    }
    
    /**
     * Updates room status based on reservation records.
     */
    public void updateRoomStatusByReservationList()
    {
        Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date current = digiClock.getTime();

        for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
        {
            if (reservationDao.getAllReservation().get(i).getCheckInDatetime().getDay() == current.getDay() &&
                    reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(4)))
            {
                if (roomManager.getRoomByRoomNum(reservationDao.getAllReservation().get(i).getRoom().getRoomNumber()).getRoomStatus().equals(RoomStatus.returnStatus(3)))
                {
                    roomManager.updateStatusBySystem(roomManager.getRoomByRoomNum(reservationDao.getAllReservation().get(i).getRoom().getRoomNumber()), RoomStatus.returnStatus(1));
                }
            }

            //make the vacant room status to reserved if the check in date is today and the reservation is valid
            if (reservationDao.getAllReservation().get(i).getCheckInDatetime().getDate() == current.getDate() &&
                    !(reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(4)) &&
                            !(reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(3)))))
            {
                if (roomManager.getRoomByRoomNum(reservationDao.getAllReservation().get(i).getRoom().getRoomNumber()).getRoomStatus().equals(RoomStatus.returnStatus(1)))
                    roomManager.updateStatusBySystem(roomManager.getRoomByRoomNum(reservationDao.getAllReservation().get(i).getRoom().getRoomNumber()), RoomStatus.returnStatus(3));
            }
        }
    }
    
    /**
     * Prints the available rooms.
     * @param availableRoomList The available room objects.
     */
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
                if (roomManager.getRoomDao().getAllRoomType().get(i).getType().equals(availableRoomList.get(j).getRoomType().getType()) &&
                        !availableRoomList.get(j).getRoomStatus().equals(RoomStatus.returnStatus(4)))
                    noOfVacant++;
            }
            System.out.println(roomManager.getRoomDao().getAllRoomType().get(i).getType() + ": Number: " + noOfVacant + " out of " + total);
            System.out.print("Rooms: ");

            for (int k = 0; k < availableRoomList.size(); k++)
            {
                if (roomManager.getRoomDao().getAllRoomType().get(i).getType().equals(availableRoomList.get(k).getRoomType().getType()) &&
                        !availableRoomList.get(k).getRoomStatus().equals(RoomStatus.returnStatus(4)))
                    System.out.print("0" + availableRoomList.get(k).getRoomNumber() + ", ");
            }

            System.out.println("");
            noOfVacant = 0;
            total = 0;
        }
    }
    
    /**
     * Updates available rooms.
     * @param availableRoomList The available room objects.
     * @param checkInDate The check in date.
     * @param checkOutDate The check out date.
     */
    public void updateAvailableRoomList(ArrayList<Room> availableRoomList, Date checkInDate, Date checkOutDate)
    {
        // Remove the unavailable room within the checkin and checkout date
        while (checkInDate.compareTo(checkOutDate) <= 0)
        {
            for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
            {
                if (checkInDate.compareTo(reservationDao.getAllReservation().get(i).getCheckInDatetime()) >= 0 &&
                        checkInDate.compareTo(reservationDao.getAllReservation().get(i).getCheckOutDatetime()) <= 0 &&
                        !reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(4)))
                {
                    for (int j = 0; j < availableRoomList.size(); j++)
                    {
                        if (availableRoomList.get(j).getRoomNumber() == reservationDao.getAllReservation().get(i).getRoom().getRoomNumber())
                        {
                            availableRoomList.remove(j);
                        }
                    }
                }
            }
            long time = checkInDate.getTime();
            time += 1000 * 60 * 60 * 24;
            checkInDate.setTime(time);
        }
    }
    
    /**
     * Updates wait list of reservation.
     */
    public void updateWaitList()
    {
        ArrayList<Reservation> waitList = new ArrayList<Reservation>();
        for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
        {
            if (reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(2)))
                waitList.add(reservationDao.getAllReservation().get(i));
        }
        availableRoomList = new ArrayList<Room>(roomManager.getAllRoom());
        Date checkInDatetime = new Date();
        String strCheckIn = null;
        for (int i = 0; i < waitList.size(); i++)
        {
            strCheckIn = dateFormat.format(waitList.get(i).getCheckInDatetime());
            try
            {
                checkInDatetime = dateFormat.parse(strCheckIn);
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
            Date checkOutDatetime = waitList.get(i).getCheckOutDatetime();
            this.updateAvailableRoomList(availableRoomList, checkInDatetime, checkOutDatetime);

            for (int k = 0; k < availableRoomList.size(); k++)
            {
                if (availableRoomList.get(k).getRoomType().getType().equals(waitList.get(i).getRoom().getRoomType().getType()) &&
                        !availableRoomList.get(k).getRoomStatus().equals(RoomStatus.returnStatus(4)))
                {
                    Room room = null;
                    for (int j = 0; j < roomManager.getAllRoom().size(); j++)
                    {
                        if (availableRoomList.get(k).getRoomNumber() == roomManager.getAllRoom().get(j).getRoomNumber())
                            room = roomManager.getAllRoom().get(j);
                    }

                    waitList.get(i).setRoom(room);
                    waitList.get(i).setReservationStatus(ReservationStatus.returnStatus(1));
                }
            }
        }
    }
    
    /**
     * Writes reservation data.
     */
    public void WritetoFile()
    {
        reservationDao.updateFile();
    }
    
    /**
     * Gets reservation DAO.
     * @return this DAO.
     */
    public ReservationDaoImpl getReservationDao()
    {
        return (ReservationDaoImpl) reservationDao;
    }
}