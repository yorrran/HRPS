/**
 * RoomServiceManager handles the adding, removing, updating and displaying process of room services.
 */
public class RoomServiceManager
{
    RoomServiceDao roomServiceDao;

    /**
     * Class constructor. Room service data object is initialized here.
     */
    public RoomServiceManager()
    {
        roomServiceDao = new RoomServiceDaoImp();
    }

    /**
     * Allows user to add a new room service.
     */
    public void addRoomService()
    {
        System.out.print("Enter Room Service Name: ");
        String service = Input.GetString();

        System.out.print("Enter Room Service Price: ");
        int price = Input.GetInt();

        RoomService newService = new RoomService(roomServiceDao.getAllRoomService().size(), service, price);
        roomServiceDao.addRoomService(newService);
    }

    /**
     * Allow user to update the existing room services.
     */
    public void updateRoomService()
    {
        int choice;

        displayRoomService();
        System.out.print("Enter the Room Service ID to be updated: ");
        int id = Input.GetInt();

        if (roomServiceDao.getAllRoomService().get(id - 1) != null)
        {
            System.out.println("Room Service to be updated: \n" +
                    "Room Service Type: " + roomServiceDao.getAllRoomService().get(id - 1).getService() + "\n" +
                    "Room Service Price: " + roomServiceDao.getAllRoomService().get(id - 1).getPrice());

            RoomService rst;

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
                        rst = roomServiceDao.getAllRoomService().get(id - 1);
                        rst.setService(type);
                        roomServiceDao.updateRoomService(id - 1, rst);
                        break;
                    case 2:
                        System.out.print("Enter a new Room Service Price: ");
                        int price = Input.GetInt();
                        rst = roomServiceDao.getAllRoomService().get(id - 1);
                        rst.setPrice(price);
                        roomServiceDao.updateRoomService(id - 1, rst);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid option! Enter again!");
                        break;
                }
            } while (choice != 0);
        }
        else
        {
            System.out.println("Room service not found.");
        }
    }

    /**
     * Allows user to remove room services
     */
    public void removeRoomService()
    {
        roomServiceDao.displayRoomService();
        System.out.print("Enter the room service ID to be removed or press 0 to cancel : ");
        int id;

        do
        {
            id = Input.GetInt();

            if (id > roomServiceDao.getAllRoomService().size())
                System.out.print("Room service not found! Enter again : ");
            else
                break;
        } while (id != 0);

        if (id == 0)
            return;

        roomServiceDao.removeRoomService(roomServiceDao.getAllRoomService().get(id - 1));
    }

    /**
     * Display all room services available
     */
    public void displayRoomService()
    {
        roomServiceDao.displayRoomService();
    }

    /**
     * Write the current list data to file.
     */
    public void WritetoFile()
    {
        roomServiceDao.updateFile();
    }

    /**
     * Returns the room service data access object so that other manager class can access it.
     *
     * @return room service data access object
     */
    public RoomServiceDaoImp getRoomServiceDao()
    {
        return (RoomServiceDaoImp) roomServiceDao;
    }
}
