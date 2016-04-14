import java.util.ArrayList;

/**
 * Bill data access object.
 */
public class BillDaoImp implements BillDao
{
    private static final String fileName = "File/Bill.dat";
    private static ArrayList<Bill> billList;

    /**
     * Class constructor. Bill is loaded from data file.
     */
    public BillDaoImp()
    {
        billList = DataIO.Read(fileName);
    }

    /**
     * Add a new bill to the existing list.
     *
     * @param bill new bill
     */
    @Override
    public void addBill(Bill bill)
    {
        billList.add(bill);
    }

    /**
     * Update a bill on the total room price.
     *
     * @param roomNum room number of the bill to be updated
     * @param amount  the total price of the room
     */
    @Override
    public void updateRoomCharge(int roomNum, double amount)
    {
        for (int i = 0; i < billList.size(); i++)
        {
            if (billList.get(i).getRoomNum() == roomNum)
                billList.get(i).setRoomCharge(billList.get(i).getRoomServiceCharge() + amount);
        }
    }

    /**
     * Update a bill on the total charge of room service ordered by the guest.
     *
     * @param roomNum room number of the bill to be updated
     * @param amount  the total price of the room service
     */
    @Override
    public void updateRoomServiceCharge(int roomNum, double amount)
    {
        for (int i = 0; i < billList.size(); i++)
        {
            if (billList.get(i).getRoomNum() == roomNum)
                billList.get(i).setRoomServiceCharge(billList.get(i).getRoomServiceCharge() + amount);
        }
    }

    /**
     * Remove a bill.
     *
     * @param bill the bill to be removed
     */
    @Override
    public void removeBill(Bill bill)
    {
        billList.remove(bill);
    }

    /**
     * Returns the list of bill so that manager class can access it.
     *
     * @return list of bills
     */
    public ArrayList<Bill> getAllBill()
    {
        return this.billList;
    }

    /**
     * Write the current list data to file.
     */
    @Override
    public void updateFile()
    {
        DataIO.Write(fileName, billList);
    }
}