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
    private DecimalFormat twoint = new DecimalFormat("00");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

    public ReservationManager(RoomManager roomManager)
    {
        reservationDao = new ReservationDaoImpl();
        this.roomManager = roomManager;
    }

    public void addReservation()
    {
        System.out.print("Enter your passport or driving license: ");
        String identity = null;
        identity = Input.GetString();

        Room room = null;
        String reservationCode = new StringBuilder().append(getCurrentDateTime()).append(identity).toString();
        //get reservation code
        System.out.println(reservationCode);

        do
        {
            System.out.print("Enter the room number: ");
            int roomNum = Input.GetInt();
            room = this.getRoomByRoomNum(roomNum);

            if (!room.getRoomStatus().equals(RoomStatus.returnStatus(1)))
            {
                System.out.println("This room is unavailable now, please select another room.");
                room = null;
            }
        } while (room == null);//selecte the vacant room


        Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date reservationDatetime = digiClock.getTime();//get reservation datetime

        System.out.print("Enter check in date(ddMMyyyy): ");
        String checkInString = Input.GetString();
        Date checkinDate = null;
        try
        {
            checkinDate = dateFormat.parse(checkInString.toString());
        } catch (ParseException e)
        {
            e.printStackTrace();
        }//get check in date

        System.out.print("Enter check out date(ddMMyyyy): ");
        String checkOutString = Input.GetString();
        Date checkOutDate = null;
        try
        {
            checkOutDate = dateFormat.parse(checkOutString.toString());
        } catch (ParseException e)
        {
            e.printStackTrace();
        }//get check out date

        long diff = checkOutDate.getTime() - checkinDate.getTime();
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));//get difference of days between check out and check in
        double price = numOfDays * (room.getRoomType().getPrice() + room.getFacing().getPrice());//get price for room type and facing type

        System.out.print("Enter number of adults: ");
        int numberOfAdults = Input.GetInt();

        System.out.print("Enter number of children: ");
        int numberOfChildren = Input.GetInt();

        Reservation reservation = new Reservation(reservationCode, identity, room, price, reservationDatetime, checkinDate, checkOutDate, numberOfAdults, numberOfChildren, ReservationStatus.returnStatus(1));
        reservationDao.addReservation(reservation);
        this.updateStatusBySystem(room, RoomStatus.returnStatus(3));//update room status to reserved

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
            System.out.println("1. Check in/out date");
            System.out.println("2. Room number");
            System.out.println("3. Number of adults");
            System.out.println("4. Number of children");
            System.out.println("5. Cancel update");
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
                    System.out.print("Enter check in date(ddMMyyyy): ");
                    String checkInString = Input.GetString();
                    Date checkinDate = null;
                    try
                    {
                        checkinDate = dateFormat.parse(checkInString.toString());
                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                    }//get check in date

                    System.out.print("Enter check out date(ddMMyyyy): ");
                    String checkOutString = Input.GetString();
                    Date checkOutDate = null;
                    try
                    {
                        checkOutDate = dateFormat.parse(checkOutString.toString());
                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                    }//get check out date

                    diff = checkOutDate.getTime() - checkinDate.getTime();
                    numOfDays = (int) (diff / (1000 * 60 * 60 * 24));//get difference of days between check out and check in
                    price = numOfDays * (reservation.getRoom().getRoomType().getPrice() + reservation.getRoom().getFacing().getPrice());//get price for room type and facing type

                    reservation.setReservationDatetime(reservationDatetime);
                    reservation.setCheckInDatetime(checkinDate);
                    reservation.setCheckOutDatetime(checkOutDate);
                    reservation.setPrice(price);
                    break;
                case 2:
                    Room room = null;
                    for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
                    {
                        if (reservationDao.getAllReservation().get(i).getRoom().getRoomNumber() == reservation.getRoom().getRoomNumber())
                        {
                            room = this.getRoomByRoomNum(reservationDao.getAllReservation().get(i).getRoom().getRoomNumber());
                            this.updateStatusBySystem(room, RoomStatus.returnStatus(1));
                        }
                    }
                    do
                    {
                        System.out.print("Enter the room number: ");
                        int roomNum = Input.GetInt();
                        room = this.getRoomByRoomNum(roomNum);

                        if (!room.getRoomStatus().equals(RoomStatus.returnStatus(1)))
                        {
                            System.out.println("This room is unavailable now, please select another room.");
                            room = null;
                        }
                    } while (room == null);//selecte the vacant room
                    
                    diff = reservation.getCheckOutDatetime().getTime() - reservation.getCheckInDatetime().getTime();
                    numOfDays = (int) (diff / (1000 * 60 * 60 * 24));//get difference of days between check out and check in
                    price = numOfDays * (room.getRoomType().getPrice() + room.getFacing().getPrice());//get price for room type and facing type
                    reservation.setReservationDatetime(reservationDatetime);
                    reservation.setRoom(room);
                    reservation.setPrice(price);
                    this.updateStatusBySystem(room, RoomStatus.returnStatus(3));//update room status to reserved

                    break;
                case 3:
                    System.out.print("Enter number of adults: ");
                    int numberOfAdults = Input.GetInt();
                    reservation.setReservationDatetime(reservationDatetime);
                    reservation.setNumOfAdult(numberOfAdults);
                    break;
                case 4:
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

            System.out.println("Reservation Code: " + reservationCode + ", Identity: " + identity + ", Reservation Date: " + dateFormat.format(reservationDatetime));
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

        System.out.println("Reservation Code: " + reservationCode + ", Identity: " + identity + ", Reservation Date: " + dateFormat.format(reservationDatetime));
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

    //make reservation expired if check in date is before current date when then status is not expired
    public void makeReservationExpired()
    {
        Calendar digiClock = Calendar.getInstance();
        digiClock.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        Date current = digiClock.getTime();
        for (int i = 0; i < reservationDao.getAllReservation().size(); i++)
        {
            if (reservationDao.getAllReservation().get(i).getCheckInDatetime().compareTo(current) < 0 &&
                    !reservationDao.getAllReservation().get(i).getReservationStatus().equals(ReservationStatus.returnStatus(4)))
            {
                reservationDao.getAllReservation().get(i).setReservationStatus(ReservationStatus.returnStatus(4));
                Room room = this.getRoomByRoomNum(reservationDao.getAllReservation().get(i).getRoom().getRoomNumber());
                this.updateStatusBySystem(room, RoomStatus.returnStatus(1));
            }
        }
    }

    public void updateStatusBySystem(Room room, RoomStatus roomStatus)
    {
        room.setRoomStatus(roomStatus);
    }

    public void WritetoFile()
    {
        reservationDao.updateFile();
    }

    public ReservationDaoImpl getReservationDao() { return (ReservationDaoImpl)reservationDao; }
}