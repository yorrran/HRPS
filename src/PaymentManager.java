/**
 * PaymentManager handles the payment process.
 */
public class PaymentManager
{
    private BillManager billManager;
    private OrderManager orderManager;

    /**
     * Class constructor.
     *
     * @param billManager  bill manager to get the amount of charges
     * @param orderManager to get the list of room service ordered
     */
    public PaymentManager(BillManager billManager, OrderManager orderManager)
    {
        this.billManager = billManager;
        this.orderManager = orderManager;
    }

    /**
     * This method is called when a guest check-out and pay for the stay.
     *
     * @param roomNum room number of the guest
     * @param days    number of days the guest stayed in the room
     */
    public void pay(int roomNum, int days)
    {
        billManager.displayBillbyRoomNum(roomNum);
        System.out.print("1. Pay by credit card\n2. Pay by cash\nInput : ");
        int choice = Input.GetInt();

        switch (choice)
        {
            case 1:
                byCreditCard(roomNum, days);
                break;
            case 2:
                byCash(roomNum, days);
                break;
            default:
                System.out.print("Invalid input! Enter again : ");
                break;
        }
    }

    /**
     * The total amount that the guest should pay is calculated.
     * If the guest chooses to pay by credit card, the process will be automated.
     *
     * @param roomNum room number of the guest
     * @param days    number of days the guest stayed in the room
     */
    public void byCreditCard(int roomNum, int days)
    {
        for (int i = 0; i < billManager.getBillDao().getAllBill().size(); i++)
        {
            if (billManager.getBillDao().getAllBill().get(i).getRoomNum() == roomNum)
            {
                double total = billManager.getBillDao().getAllBill().get(i).getRoomCharge() + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge();
                double discount = total * billManager.getBillDao().getAllBill().get(i).getDiscount();
                double taxable = billManager.getBillDao().getAllBill().get(i).getTax() * total;
                double payable = total - discount + taxable;

                String orderItems = "";

                for (int j = 0; j < orderManager.getOrderDao().getOrderByRoomNum(roomNum).getRoomServiceList().size(); j++)
                {
                    orderItems += "\t" + (i + 1) + ". " + orderManager.getOrderDao().getOrderByRoomNum(roomNum).getRoomServiceList().get(i).getService() +
                            "\t$" + orderManager.getOrderDao().getOrderByRoomNum(roomNum).getRoomServiceList().get(i).getPrice() + "\n";
                }

                System.out.println("----------------------------------------------------------------------------" + "\n" +
                        "Bill number : " + billManager.getBillDao().getAllBill().get(i).getBillNum() +
                        "\tRoom Number : " + billManager.getBillDao().getAllBill().get(i).getRoomNum() + "\n" +
                        "Days of stay : " + days + "\n" +
                        "Room Charge : " + billManager.getBillDao().getAllBill().get(i).getRoomCharge() + "\n" +
                        "Room Service Charge : " + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge() + "\n" +
                        "\tOrder items : " + "\n" + orderItems +
                        "Total : " + total + "\n" +
                        "Tax : " + taxable + "\n" +
                        "Total amount : " + payable + "\n" +
                        "----------------------------------------------------------------------------" + "\n" +
                        "$" + payable + " has been paid for room " + roomNum + " through credit card!");

                billManager.removeBill(roomNum);
                orderManager.removeOrder(roomNum);
            }
        }
    }

    /**
     * The total amount that the guest should pay is calculated.
     * If the guest chooses to pay by cash, enter the amount of cash paid by the guest.
     *
     * @param roomNum the room number of the guest
     * @param days    number of days the guest stayed in the room
     */
    public void byCash(int roomNum, int days)
    {
        for (int i = 0; i < billManager.getBillDao().getAllBill().size(); i++)
        {
            if (billManager.getBillDao().getAllBill().get(i).getRoomNum() == roomNum)
            {
                double total = billManager.getBillDao().getAllBill().get(i).getRoomCharge() +
                        billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge();
                double discount = total * billManager.getBillDao().getAllBill().get(i).getDiscount();
                double taxable = billManager.getBillDao().getAllBill().get(i).getTax() * total;
                double payable = total - discount + taxable;

                System.out.print("Amount : ");
                int amount = Input.GetInt();

                String orderItems = "";

                for (int j = 0; j < orderManager.getOrderDao().getOrderByRoomNum(roomNum).getRoomServiceList().size(); j++)
                {
                    orderItems += "\t" + (i + 1) + ". " + orderManager.getOrderDao().getOrderByRoomNum(roomNum).getRoomServiceList().get(i).getService() +
                            "\t$" + orderManager.getOrderDao().getOrderByRoomNum(roomNum).getRoomServiceList().get(i).getPrice() + "\n";
                }

                System.out.println("----------------------------------------------------------------------------" + "\n" +
                        "Bill number : " + billManager.getBillDao().getAllBill().get(i).getBillNum() +
                        "\tRoom Number : " + billManager.getBillDao().getAllBill().get(i).getRoomNum() + "\n" +
                        "Days of stay : " + days + "\n" +
                        "Room Charge : " + billManager.getBillDao().getAllBill().get(i).getRoomCharge() + "\n" +
                        "Room Service Charge : " + billManager.getBillDao().getAllBill().get(i).getRoomServiceCharge() + "\n" +
                        "\tOrder items : " + "\n" + orderItems +
                        "Total : " + total + "\n" +
                        "Tax : " + taxable + "\n" +
                        "Total amount : " + payable + "\n" +
                        "----------------------------------------------------------------------------" + "\n" +
                        "$" + payable + " has been paid for room " + roomNum + "!\n" +
                        "Amount : " + amount + "\n" +
                        "Change : " + (amount - total));

                billManager.removeBill(roomNum);
                orderManager.removeOrder(roomNum);
            }
        }
    }
}