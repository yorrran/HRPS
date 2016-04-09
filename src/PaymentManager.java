public class PaymentManager
{
    private BillManager billManager;
    private OrderManager orderManager;

    public PaymentManager(BillManager billManager, OrderManager orderManager)
    {
        this.billManager = billManager;
        this.orderManager = orderManager;
    }

    public void paybyCreditCard(int roomNum)
    {
        for (int i = 0; i < billManager.getBillDao().getAllBill().size(); i++)
        {
            if (billManager.getBillDao().getAllBill().get(i).getRoomNum() == roomNum)
            {
                double total = billManager.getBillDao().getAllBill().get(i).getRoomCharge() + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge();
                double discount = total * billManager.getBillDao().getAllBill().get(i).getDiscount();
                double taxable = billManager.getBillDao().getAllBill().get(i).getTax() * total;
                double payable = total - discount + taxable;

                System.out.println("Bill number : " + billManager.getBillDao().getAllBill().get(i).getBillNum() + "\n" +
                        "Room Number\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomNum() + "\n" +
                        "Room Charge\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomCharge() + "\n" +
                        "Room Service Charge\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge() + "\n" +
                        "Total\t:\t" + total + "\n" +
                        "Tax\t:\t" +  taxable + "\n" +
                        "Room Service Charge\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge() + "\n" +
                        "$" + payable + " has been paid for room " + roomNum + "!");

                billManager.removeBill(roomNum);
                orderManager.removeOrder(roomNum);
            }
        }
    }

    public void paybyCash(int roomNum)
    {

    }
}