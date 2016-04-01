public class RoomServiceManager
{
    RoomServiceDao roomServiceDao;

    public RoomServiceManager()
    {
        roomServiceDao = new RoomServiceDaoImp();
    }

    public void addRoomService()
    {
        System.out.print("Enter Room Service Name: ");
        String service = Input.GetString();

        System.out.print("Enter Room Service Price: ");
        int price = Input.GetInt();

        RoomServiceType newService = new RoomServiceType(roomServiceDao.getAllRoomService().size() + 1, service, price);
        roomServiceDao.addRoomService(newService);
    }

    public void updateRoomService()
    {
        int choice;

        displayRoomService();
        System.out.print("Enter the Room Service ID to be updated: ");
        int updateRoomServiceId = Input.GetInt();

        for (int i = 0; i < roomServiceDao.getAllRoomService().size(); i++)
        {
            if (roomServiceDao.getAllRoomService().get(i).getId() == updateRoomServiceId)
            {
                System.out.println("Room Service to be updated: \n" +
                        "Room Service Type: " + roomServiceDao.getAllRoomService().get(i).getService() + "\n" +
                        "Room Service Price: " + roomServiceDao.getAllRoomService().get(i).getPrice());

                RoomServiceType rst;

                do
                {
                    System.out.println("\n\nEnter your choice:\n" +
                            "1. Update Room Service Type\n" +
                            "2. Update Room Service Price\n" +
                            "0. Cancel");

                    choice = Input.GetInt();

                    switch (choice)
                    {
                        case 1:
                            System.out.print("Enter a new Room Service Type: ");
                            String type = Input.GetString();
                            rst = roomServiceDao.getAllRoomService().get(i);
                            rst.setService(type);
                            roomServiceDao.updateRoomService(rst);
                            break;
                        case 2:
                            System.out.print("Enter a new Room Service Price: ");
                            int price = Input.GetInt();
                            rst = roomServiceDao.getAllRoomService().get(i);
                            rst.setPrice(price);
                            roomServiceDao.updateRoomService(rst);
                            break;
                        default:
                            System.out.println("Invalid option! Enter again!");
                            break;
                    }
                } while (choice != 0);
            }
        }
    }

    public void removeRoomService()
    {
        roomServiceDao.displayRoomService();
        System.out.print("Enter the room ID to be removed or press 0 to cancel : ");
        int removeServiceId;

        do
        {
            removeServiceId = Input.GetInt();

            if (removeServiceId > roomServiceDao.getAllRoomService().size())
                System.out.print("Room service not found! Enter again : ");
            else
                break;
        } while (removeServiceId != 0);

        if (removeServiceId == 0)
            return;

        for (int i = 0; i < roomServiceDao.getAllRoomService().size(); i++)
        {
            if (roomServiceDao.getAllRoomService().get(i).getId() == removeServiceId)
            {
                roomServiceDao.removeRoomService(roomServiceDao.getAllRoomService().get(i));
                break;
            }
        }
    }

    public void displayRoomService()
    {
        roomServiceDao.displayRoomService();
    }

    public void WritetoFile()
    {
        roomServiceDao.updateFile();
    }

    public RoomServiceDaoImp getRoomServiceDao() { return (RoomServiceDaoImp)roomServiceDao; }
}
