import java.util.ArrayList;

public class BillDaoImp implements BillDao
{
    private static final String fileName = "File/Bill.dat";
    private static ArrayList<Bill> billList;

    public BillDaoImp()
    {
        billList = DataIO.Read(fileName);
    }

    public ArrayList<Bill> getAllBill()
    {
        return this.billList;
    }

    @Override
    public void addBill(Bill bill)
    {
        billList.add(bill);
    }

    @Override
    public void updateRoomCharge(int roomNum, double amount)
    {
        for (int i = 0; i < billList.size(); i++)
        {
            if (billList.get(i).getRoomNum() == roomNum)
                billList.get(i).setRoomCharge(billList.get(i).getRoomServiceCharge() + amount);
        }
    }

    @Override
    public void updateRoomServiceCharge(int roomNum, double amount)
    {
        for (int i = 0; i < billList.size(); i++)
        {
            if (billList.get(i).getRoomNum() == roomNum)
                billList.get(i).setRoomServiceCharge(billList.get(i).getRoomServiceCharge() + amount);
        }
    }

    @Override
    public void removeBill(Bill bill)
    {
        billList.remove(bill);
    }

    @Override
    public void updateFile()
    {
        DataIO.Write(fileName, billList);
    }
}