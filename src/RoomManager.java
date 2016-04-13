import java.util.ArrayList;
import java.util.Scanner;

public class RoomManager
{
    private RoomDao roomDao;
  
    public RoomManager()
    {
        roomDao = new RoomDaoImpl();
    }

    public void displayAllRoom()
    {
        for (int i = 0; i < roomDao.getAllRoom().size(); i++)
        {
            int roomNumber = roomDao.getAllRoom().get(i).getRoomNumber();
            String roomType = roomDao.getAllRoom().get(i).getRoomType().getType();
            String bedType = roomDao.getAllRoom().get(i).getBedType().toString();
            String facing = roomDao.getAllRoom().get(i).getFacing().getFacingType();

            String isWifi;
            isWifi = roomDao.getAllRoom().get(i).getWifi() ? "Yes" : "No";

            String isSmoking;
            isSmoking = roomDao.getAllRoom().get(i).getSmoking() ? "Yes" : "No";

            String status = roomDao.getAllRoom().get(i).getRoomStatus().toString();
            System.out.println("Room Number: 0" + roomNumber + ", Status: " + status);
            System.out.println("Room Type: " + roomType + ", Bed Type: " + bedType + " bed" + ", Facing: " + facing + ", WIFI: " + isWifi + ", Smoking: " + isSmoking);
            System.out.println("---------------------------------------");
        }
    }

    public void updateRoom()
    {
        Room room = null;

        System.out.print("Select the room number to update: ");
        int roomNum = Input.GetInt();
        room = roomDao.getRoomByRoomNum(roomNum);

        System.out.println("Details ");
        System.out.println("1. Bed type");
        System.out.println("2. Wifi");
        System.out.println("3. Smoking");
        System.out.println("4. Status");
        System.out.println("5. Cancel update");
        System.out.print("Select which detail you want to update: ");
        int choice = Input.GetInt();
        while (choice < 1 || choice > 5)
        {
            System.out.print("Please enter correct choice: ");
            choice = Input.GetInt();
        }
        System.out.println("");
        switch (choice)
        {
            case 1:
                updateRoomBedType(room);
                break;
            case 2:
                updateRoomWifi(room);
                break;
            case 3:
                updateRoomSmoking(room);
                break;
            case 4:
            	updateRoomStatusByStaff(room);
                break;
            default:
                System.out.println("Update canceled.");
        }
    }

    public void updateRoomBedType(Room room)
    {
        RoomBedType.printRoomBedType();
        System.out.print("Select new bad type for this room: ");
        int choiceOfBedType = Input.GetInt();
        System.out.println("");
        while (choiceOfBedType < 1 || choiceOfBedType > 3)
        {
            System.out.print("Please enter a correct bad type number:");
            choiceOfBedType = Input.GetInt();
            System.out.println("");
        }
        room.setBedType(RoomBedType.returnRoomBedType(choiceOfBedType));
    }

    public void updateRoomWifi(Room room)
    {
        System.out.println("1. Enable Wifi");
        System.out.println("2. Disable Wifi");
        System.out.print("Select to enable/disable wifi for the room: ");
        int choiceOfWifi = Input.GetInt();
        
        System.out.println("");
        
        while (choiceOfWifi < 1 || choiceOfWifi > 2)
        {
            System.out.print("Please enter a correct choice:");
            choiceOfWifi = Input.GetInt();
            System.out.println("");
        }
        
        if (choiceOfWifi == 1)
            room.setWifi(true);
        else
            room.setWifi(false);

    }

    public void updateRoomSmoking(Room room)
    {
        System.out.println("1. Smoking");
        System.out.println("2. Non-smoking");
        System.out.print("Select to smoking/non-smoking for the room: ");
        int choiceOfSmoking = Input.GetInt();
        System.out.println("");
        
        while (choiceOfSmoking < 1 || choiceOfSmoking > 2)
        {
            System.out.print("Please enter a correct choice:");
            choiceOfSmoking = Input.GetInt();
            System.out.println("");
        }

        if (choiceOfSmoking == 1)
            room.setSmoking(true);
        else
            room.setSmoking(false);
    }
    
    public void updateRoomStatusByStaff(Room room)
    {   
    	System.out.println("Room status:");
    	System.out.println("1. set to under maintenance");
    	System.out.println("2. set to vacant");
    	System.out.print("Select the status for this room: ");
        int choiceOfStatus = Input.GetInt();
        System.out.println("");
        while (choiceOfStatus < 1 || choiceOfStatus > 2)
        {
            System.out.print("Please enter a correct status number:");
            choiceOfStatus = Input.GetInt();
            System.out.println("");
        }
        if(room.getRoomStatus().equals(RoomStatus.returnStatus(2)) || room.getRoomStatus().equals(RoomStatus.returnStatus(3)))
        {
        	System.out.println("Room status for this roomcannot be updated at this moment\n");
        }
        else
        {
        	if(choiceOfStatus == 1)
        	{
        		room.setRoomStatus(RoomStatus.returnStatus(4));
        	}
        	else if(choiceOfStatus ==2)
        	{
        		room.setRoomStatus(RoomStatus.returnStatus(1));
        	}
        	System.out.println("Room status updated\n");
        }
    }
    
    public void updateStatusBySystem(Room room, RoomStatus roomStatus)
    {
        room.setRoomStatus(roomStatus);
    }

    //public void updateRoomStatus
    
    public void displayVacantRoom()
    {
        int noOfVacant = 0, total = 0;
        System.out.println("Status statistic report for vacant room");
        for (int i = 0; i < roomDao.getAllRoomType().size(); i++)
        {
            for (int j = 0; j < roomDao.getAllRoom().size(); j++)
            {
                if (roomDao.getAllRoomType().get(i).getType().equals(roomDao.getAllRoom().get(j).getRoomType().getType()))
                {
                    total++;
                }
                if (roomDao.getAllRoomType().get(i).getType().equals(roomDao.getAllRoom().get(j).getRoomType().getType()) && roomDao.getAllRoom().get(j).getRoomStatus().equals(RoomStatus.returnStatus(1)))
                {
                    noOfVacant++;
                }
            }

            System.out.println(roomDao.getAllRoomType().get(i).getType() + ": Number: " + noOfVacant + " out of " + total);
            System.out.print("Rooms: ");
            for (int k = 0; k < roomDao.getAllRoom().size(); k++)
            {
                if (roomDao.getAllRoomType().get(i).getType().equals(roomDao.getAllRoom().get(k).getRoomType().getType()) && roomDao.getAllRoom().get(k).getRoomStatus().equals(RoomStatus.returnStatus(1)))
                    System.out.print("0" + roomDao.getAllRoom().get(k).getRoomNumber() + ", ");
            }
            System.out.println("");
            noOfVacant = 0;
            total = 0;
        }


    }

    public void displayStatusByRoom()
    {

        System.out.println("\nVacant: ");
        System.out.print("Rooms: ");
        for (int i = 0; i < roomDao.getAllRoom().size(); i++)
            if (roomDao.getAllRoom().get(i).getRoomStatus() == RoomStatus.returnStatus(1))
                System.out.print(roomDao.getAllRoom().get(i).getRoomNumber() + ", ");
        System.out.println("");

        System.out.println("\nOccupied: ");
        System.out.print("Rooms: ");
        for (int i = 0; i < roomDao.getAllRoom().size(); i++)
            if (roomDao.getAllRoom().get(i).getRoomStatus() == RoomStatus.returnStatus(2))
                System.out.print(roomDao.getAllRoom().get(i).getRoomNumber() + ", ");
        System.out.println("");

        System.out.println("\nReserved: ");
        System.out.print("Rooms: ");
        for (int i = 0; i < roomDao.getAllRoom().size(); i++)
            if (roomDao.getAllRoom().get(i).getRoomStatus() == RoomStatus.returnStatus(3))
                System.out.print(roomDao.getAllRoom().get(i).getRoomNumber() + ", ");
        System.out.println("");

        System.out.println("\nUnderMaintenance: ");
        System.out.print("Rooms: ");
        for (int i = 0; i < roomDao.getAllRoom().size(); i++)
            if (roomDao.getAllRoom().get(i).getRoomStatus() == RoomStatus.returnStatus(4))
                System.out.print(roomDao.getAllRoom().get(i).getRoomNumber() + ", ");
        System.out.println("");
    }

    public void displayRoomType()
    {
        for (int i = 0; i < roomDao.getAllRoomType().size(); i++)
        {
            System.out.println(roomDao.getAllRoomType().get(i).getTypeId() + ". " + roomDao.getAllRoomType().get(i).getType() + " Price $" + roomDao.getAllRoomType().get(i).getPrice());
        }
    }

    public void addRoomType()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name for new room type: ");
        String typeName = sc.next();
        System.out.print("Input the price for this type: ");
        double price = Input.GetDouble();
        RoomType newtype = new RoomType(roomDao.getAllRoomType().size() + 1, typeName, price);
        roomDao.addRoomType(newtype);
    }

    public void updateRoomPrice()
    {
        displayRoomType();
        System.out.print("Select the room type to update price: ");
        int roomtypeId = Input.GetInt();
        while (roomtypeId > roomDao.getAllRoomType().size() || roomtypeId < 1)
        {
            System.out.print("Please select a correct room type:");
            roomtypeId = Input.GetInt();
        }
        System.out.print("Input the new price: ");
        double price = Input.GetDouble();

        for (int i = 0; i < roomDao.getAllRoomType().size(); i++)
        {
            if (roomtypeId == roomDao.getAllRoomType().get(i).getTypeId())
            {
                roomDao.getAllRoomType().get(i).setPrice(price);
            }
        }

    }

    public void removeRoomType()
    {
        displayRoomType();
        ArrayList<RoomType> newRoomTypeList = new ArrayList();
        System.out.print("Select the room type to remove: ");
        int roomtypeId = Input.GetInt();
        while (roomtypeId > roomDao.getAllRoomType().size() || roomtypeId < 1)
        {
            System.out.print("Please select a correct room type:");
            roomtypeId = Input.GetInt();
        }
        for (int i = 0; i < roomDao.getAllRoomType().size(); i++)
        {
            if (roomtypeId == roomDao.getAllRoomType().get(i).getTypeId())
            {
                RoomType roomType = roomDao.getAllRoomType().get(i);
                roomDao.removeRoomType(roomType);
                break;
            }
        }

    }

    
    public void displayRoomFacing()
    {
        for (int i = 0; i < roomDao.getAllRoomFacing().size(); i++)
        {
            System.out.println(roomDao.getAllRoomFacing().get(i).getFacingId() + ". " + roomDao.getAllRoomFacing().get(i).getFacingType() +
                    " Price $" + roomDao.getAllRoomFacing().get(i).getPrice());
        }
    }

    public void addRoomFacing()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name for new facing type: ");
        String facingName = sc.next();
        System.out.print("Input the price for this facing type: ");
        double price = Input.GetDouble();
        RoomFacing facingtype = new RoomFacing(roomDao.getAllRoomFacing().size() + 1, facingName, price);
        roomDao.addRoomFacing(facingtype);
    }

    public void updateFacingPrice()
    {
        System.out.print("Select the facing type to update price: ");
        int roomFacingId = Input.GetInt();
        while (roomFacingId > roomDao.getAllRoomFacing().size() || roomFacingId < 1)
        {
            System.out.print("Please select a correct facing type:");
            roomFacingId = Input.GetInt();
        }
        System.out.print("Input the new price: ");
        double price = Input.GetDouble();

        for (int i = 0; i < roomDao.getAllRoomFacing().size(); i++)
        {
            if (roomFacingId == roomDao.getAllRoomFacing().get(i).getFacingId())
            {
                roomDao.getAllRoomFacing().get(i).setPrice(price);
            }
        }
    }

    public void displayRoom(int roomNum)
    {
        Room r = roomDao.getRoomByRoomNum(roomNum);
        String isWifi;
        isWifi = r.getWifi() ? "Yes" : "No";

        String isSmoking;
        isSmoking = r.getSmoking() ? "Yes" : "No";

        String status = r.getRoomStatus().toString();
        System.out.println("Room Number: 0" + r.getRoomNumber() + ", Status: " + r.getRoomStatus().toString());
        System.out.println("Room Type: " + r.getRoomType().getType() + ", Bed Type: " + r.getBedType().toString() + " bed" + ", Facing: " + r.getFacing().getFacingType() +
                ", WIFI: " + isWifi + ", Smoking: " + isSmoking);
    }

    public Room getRoomByRoomNum(int roomNum)
    {
        Room r = roomDao.getRoomByRoomNum(roomNum);
        return r;
    }

    public ArrayList<Room> getAllRoom()
    {
        return roomDao.getAllRoom();
    }

    public void WritetoFile()
    {
        roomDao.updateFile();
    }

    public RoomDaoImpl getRoomDao()
    {
        return (RoomDaoImpl)roomDao;
    }
}