import java.util.ArrayList;

public class RoomServiceDaoImp implements RoomServiceDao
{
    private static final String fileName = "File/RoomServiceType.dat";
    private static ArrayList<RoomServiceType> roomServiceList = new ArrayList<RoomServiceType>();

    public RoomServiceDaoImp()
    {
//        roomServiceList.add(new RoomServiceType(1, "Breakfast", 5));
//        roomServiceList.add(new RoomServiceType(2, "Lunch", 10));
//        roomServiceList.add(new RoomServiceType(3, "Dinner", 15));
//        DataIO.Write(fileName, roomServiceList);
        roomServiceList = DataIO.Read(fileName);
    }

    @Override
    public void addRoomService(RoomServiceType rst)
    {
        roomServiceList.add(rst);
        System.out.println("Room Service Added Successfully! ");
    }

    @Override
    public void updateRoomService(RoomServiceType rst)
    {
        roomServiceList.get(rst.getId() - 1).setService(rst.getService());
        roomServiceList.get(rst.getId() - 1).setPrice(rst.getPrice());
        System.out.println("Room Service updated successfully!");
    }

    @Override
    public void removeRoomService(RoomServiceType rst)
    {
        roomServiceList.remove(rst.getId() - 1);
        System.out.println("Room service removed!");
    }

    @Override
    public void displayRoomService()
    {
        System.out.println("---------- Room Service List ----------");
        for (int i = 0; i < roomServiceList.size(); i++)
        {
            System.out.println(roomServiceList.get(i).getId() + ". " + roomServiceList.get(i).getService() + "\t$" +
                    roomServiceList.get(i).getPrice());
        }
        System.out.println("------------------------------");
    }

    @Override
    public ArrayList<RoomServiceType> getAllRoomService()
    {
        return roomServiceList;
    }

    @Override
    public void updateFile()
    {
        DataIO.Write(fileName, roomServiceList);
    }
}