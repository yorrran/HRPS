/**
 *BillManager stores the total charge of the room and room services ordered.
 */
public class BillManager
{
    private BillDao billDao;

    /**
     * Class Constructor. Bill data object is initialized here.
     */
    public BillManager()
    {
        billDao = new BillDaoImp();
    }

    /**
     *Create a bill when a new guest checks in into a room.
     * @param roomNum room number of the new guest
     */
    public void createBill(int roomNum)
    {
        Bill newBill = new Bill(billDao.getAllBill().size() + 1, roomNum, 0, 0, 0);
        billDao.addBill(newBill);
    }

    /**
     * Allows the order room service manager to update the total room service charge when the guest adds a new order.
     * @param roomNum room number of guest that ordered a room service
     * @param newPrice the price of the room service ordered
     */
    public void updateRoomServiceCharge(int roomNum, double newPrice)
    {
        billDao.updateRoomServiceCharge(roomNum, newPrice);
    }

    /**
     * Allows payment manager to remove the bill when the guest checks out.
     * @param roomNum room number of the guest that checks out
     */
    public void removeBill(int roomNum)
    {
        for (int i = 0; i < billDao.getAllBill().size(); i++)
        {
            if (billDao.getAllBill().get(i).getRoomNum() == roomNum)
                billDao.removeBill(billDao.getAllBill().get(i));
        }
    }

    /**
     * Display the bill of a specific room
     * @param roomNum room number of the bill to be displayed
     */
    public void displayBillbyRoomNum(int roomNum)
    {
        for (int i = 0; i < billDao.getAllBill().size(); i++)
        {
            if (billDao.getAllBill().get(i).getRoomNum() == roomNum)
            {
                System.out.println("Bill Number : " + billDao.getAllBill().get(i).getBillNum() + "\t\t" +
                        "Room Number : " + billDao.getAllBill().get(i).getRoomNum() + "\t\t" +
                        "Room Charge : " + billDao.getAllBill().get(i).getRoomCharge() + "\t\t" +
                        "Room Service Charge : " + billDao.getAllBill().get(i).getRoomServiceCharge() + "\t\t" +
                        "Discount : " + billDao.getAllBill().get(i).getDiscount());

                break;
            }
        }
    }

    /**
     * Write the current list data to file.
     */
    public void WritetoFile()
    {
        billDao.updateFile();
    }

    /**
     * Returns the room service data access object so that other manager class can access it.
     * @return bill data access object
     */
    public BillDaoImp getBillDao()
    {
        return (BillDaoImp) billDao;
    }
}