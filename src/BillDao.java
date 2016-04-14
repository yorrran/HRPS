import java.util.ArrayList;

/**
 * Interface for bill data access object.
 */
public interface BillDao
{
    ArrayList<Bill> getAllBill();

    void addBill(Bill bill);

    void updateRoomCharge(int roomNum, double amount);

    void updateRoomServiceCharge(int roomNum, double price);

    void removeBill(Bill bill);

    void updateFile();
}