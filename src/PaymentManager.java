public class PaymentManager
{
    private BillManager billManager;
    private OrderManager orderManager;

    public PaymentManager(BillManager billManager, OrderManager orderManager)
    {
        this.billManager = billManager;
        this.orderManager = orderManager;
    }

    public void pay(int roomNum)
    {
        billManager.displayBillbyRoomNum(roomNum);
        System.out.print("1. Pay by credit card\n2. Pay by cash\nInput : ");
        int choice = Input.GetInt();

        switch (choice)
        {
            case 1:
                byCreditCard(roomNum);
                break;
            case 2:
                byCash(roomNum);
                break;
            default:
                System.out.print("Invalid input! Enter again : ");
                break;
        }
    }

    public void byCreditCard(int roomNum)
    {
        for (int i = 0; i < billManager.getBillDao().getAllBill().size(); i++)
        {
            if (billManager.getBillDao().getAllBill().get(i).getRoomNum() == roomNum)
            {
                double total = billManager.getBillDao().getAllBill().get(i).getRoomCharge() + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge();
                double discount = total * billManager.getBillDao().getAllBill().get(i).getDiscount();
                double taxable = billManager.getBillDao().getAllBill().get(i).getTax() * total;
                double payable = total - discount + taxable;

                System.out.println("\n\nBill number : " + billManager.getBillDao().getAllBill().get(i).getBillNum() + "\n" +
                        "Room Number\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomNum() + "\n" +
                        "Room Charge\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomCharge() + "\n" +
                        "Room Service Charge\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge() + "\n" +
                        "Total\t:\t" + total + "\n" +
                        "Tax\t:\t" + taxable + "\n" +
                        "Room Service Charge\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge() + "\n" +
                        "$" + payable + " has been paid for room " + roomNum + " through credit card!");

                billManager.removeBill(roomNum);
                orderManager.removeOrder(roomNum);
            }
        }
    }

    public void byCash(int roomNum)
    {
        for (int i = 0; i < billManager.getBillDao().getAllBill().size(); i++)
        {
            if (billManager.getBillDao().getAllBill().get(i).getRoomNum() == roomNum)
            {
                double total = billManager.getBillDao().getAllBill().get(i).getRoomCharge() + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge();
                double discount = total * billManager.getBillDao().getAllBill().get(i).getDiscount();
                double taxable = billManager.getBillDao().getAllBill().get(i).getTax() * total;
                double payable = total - discount + taxable;

                System.out.print("Amount : ");
                int amount = Input.GetInt();

                System.out.println("\n\nBill number : " + billManager.getBillDao().getAllBill().get(i).getBillNum() + "\n" +
                        "Room Number\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomNum() + "\n" +
                        "Room Charge\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomCharge() + "\n" +
                        "Room Service Charge\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge() + "\n" +
                        "Total\t:\t" + total + "\n" +
                        "Tax\t:\t" + taxable + "\n" +
                        "Room Service Charge\t:\t" + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge() + "\n" +
                        "$" + payable + " has been paid for room " + roomNum + "!\n" +
                        "Amount : " + amount + "\n" +
                        "Change : " + (amount - total));

                billManager.removeBill(roomNum);
                orderManager.removeOrder(roomNum);
            }
        }
    }
}