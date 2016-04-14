import java.util.ArrayList;

/**
 *
 */
public class RoomServiceDaoImp implements RoomServiceDao
{
    private static final String fileName = "File/RoomServiceType.dat";
    private static ArrayList<RoomServiceType> roomServiceList = new ArrayList<RoomServiceType>();

    /**
     *
     */
    public RoomServiceDaoImp()
    {
        roomServiceList = DataIO.Read(fileName);
    }

    /**
     *
     * @param rst
     */
    @Override
    public void addRoomService(RoomServiceType rst)
    {
        roomServiceList.add(rst);
        System.out.println("Room Service Added Successfully! ");
    }

    /**
     *
     * @param index
     * @param rst
     */
    @Override
    public void updateRoomService(int index, RoomServiceType rst)
    {
        roomServiceList.get(index).setService(rst.getService());
        roomServiceList.get(index).setPrice(rst.getPrice());
        System.out.println("Room Service updated successfully!");
    }

    /**
     *
     * @param rst
     */
    @Override
    public void removeRoomService(RoomServiceType rst)
    {
        roomServiceList.remove(rst);
        System.out.println("Room service removed!");
    }

    /**
     *
     */
    @Override
    public void displayRoomService()
    {
        System.out.println("---------- Room Service List ----------");

        for (int i = 0; i < roomServiceList.size(); i++)
        {
            System.out.println((i + 1) + ". " + roomServiceList.get(i).getService() + "\t$" +
                    roomServiceList.get(i).getPrice());
        }

        System.out.println("------------------------------");
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<RoomServiceType> getAllRoomService()
    {
        return roomServiceList;
    }

    /**
     *
     */
    @Override
    public void updateFile()
    {
        DataIO.Write(fileName, roomServiceList);
    }
}