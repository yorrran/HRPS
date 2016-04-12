public class BillManager
{
    private BillDao billDao;

    public BillManager()
    {
        billDao = new BillDaoImp();
    }

    public void createBill(int roomNum)
    {
        Bill newBill = new Bill(billDao.getAllBill().size() + 1, roomNum, 0, 0, 0);
        billDao.addBill(newBill);
    }

    public void updateRoomCharge(int roomNum, double newPrice)
    {
        billDao.updateRoomServiceCharge(roomNum, newPrice);
    }

    public void updateRoomServiceCharge(int roomNum, double newPrice)
    {
        billDao.updateRoomServiceCharge(roomNum, newPrice);
    }

    public void removeBill(int roomNum)
    {
        for (int i = 0; i < billDao.getAllBill().size(); i++)
        {
            if (billDao.getAllBill().get(i).getRoomNum() == roomNum)
                billDao.removeBill(billDao.getAllBill().get(i));
        }
    }

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

    public void displayBill()
    {
        for (int i = 0; i < billDao.getAllBill().size(); i++)
        {
            System.out.println("Bill Number : " + billDao.getAllBill().get(i).getBillNum() + "\t\t" +
                    "Room Number : " + billDao.getAllBill().get(i).getRoomNum() + "\t\t" +
                    "Room Charge : " + billDao.getAllBill().get(i).getRoomCharge() + "\t\t" +
                    "Room Service Charge : " + billDao.getAllBill().get(i).getRoomServiceCharge() + "\t\t" +
                    "Discount : " + billDao.getAllBill().get(i).getDiscount());
        }
    }

    public void WritetoFile()
    {
        billDao.updateFile();
    }

    public BillDaoImp getBillDao()
    {
        return (BillDaoImp) billDao;
    }
}