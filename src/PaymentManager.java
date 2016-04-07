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
                double total = billManager.getBillDao().getAllBill().get(i).getRoomCharge() +
                        billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge();

                double payable = total - total * billManager.getBillDao().getAllBill().get(i).getDiscount();

                System.out.println("$" + payable + " has been paid for room " + roomNum + "!");

                billManager.removeBill(roomNum);
                orderManager.removeOrder(roomNum);
            }
        }
    }

    public void paybyCash(int roomNum)
    {

    }
}