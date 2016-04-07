import java.util.ArrayList;

public interface BillDao
{
    ArrayList<Bill> getAllBill();

    void addBill(Bill bill);

    void updateRoomCharge(int roomNum, double amount);

    void updateRoomServiceCharge(int roomNum, double price);

    void removeBill(Bill bill);

    void updateFile();
}