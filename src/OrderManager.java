import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class OrderManager
{
    private OrderDao orderDao;
    private BillManager billManager;
    private RoomManager roomManager;
    private RoomServiceManager roomServiceManager;
    private Scanner sc = new Scanner(System.in);

    public OrderManager(BillManager billManager, RoomManager roomManager, RoomServiceManager roomServiceManager)
    {
        orderDao = new OrderDaoImpl();
        this.billManager = billManager;
        this.roomManager = roomManager;
        this.roomServiceManager = roomServiceManager;
    }

    public void createOrder(int roomNum)
    {
        orderDao.addOrder(new Order(roomNum));
    }

    public void addOrder()
    {
        System.out.print("Enter room number : ");
        int roomNum = Input.GetInt();

        for (int i = 0; i < orderDao.getAllOrder().size(); i++)
        {
            if (orderDao.getAllOrder().get(i).getRoomNumber() == roomNum)
            {
                System.out.println("What to order? ");
                roomServiceManager.displayRoomService();

                System.out.print("Choice : ");
                int roomServiceId = Input.GetInt();

                System.out.print("Enter remark : ");
                String newRemark = sc.nextLine();

                Date date = new Date();

                RoomServiceType newRoomService = roomServiceManager.getAllRoomService().get(roomServiceId - 1);
                orderDao.addRoomServicetoOrder(roomNum, date, newRoomService, newRemark, OrderStatus.OrderReceived);

                billManager.updateRoomServiceCharge(roomNum, newRoomService.getPrice());

                break;
            }
            else if (i == orderDao.getAllOrder().size())
            {
                System.out.print("Please enter a correct room number : ");
                roomNum = Input.GetInt();
                i = 0;
            }
        }
    }

    public void updateOrder()
    {
        SimpleDateFormat formatDate = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        System.out.print("Enter the room number : ");
        int roomNum = Input.GetInt();
        orderDao.getOrderByRoomNum(roomNum);

        System.out.println("Room Number : " + roomNum);

        if (orderDao.getOrderByRoomNum(roomNum).getRoomServiceList().size() != 0)
        {
            for (int i = 0; i < orderDao.getOrderByRoomNum(roomNum).getRoomServiceList().size(); i++)
            {
                System.out.println((i + 1) + ".\t\tDate & Time: " + formatDate.format(orderDao.getOrderByRoomNum(roomNum).getDateList().get(i)) +
                        " Service Name : " + orderDao.getOrderByRoomNum(roomNum).getRoomServiceList().get(i).getService() +
                        " Price : " + orderDao.getOrderByRoomNum(roomNum).getRoomServiceList().get(i).getPrice() +
                        " Order Status : " + orderDao.getOrderByRoomNum(roomNum).getStatusList().get(i) +
                        " Order Remark : " + orderDao.getOrderByRoomNum(roomNum).getRemarkList().get(i));
            }

            int item;

            do
            {
                System.out.println("Choose the order to update service or Press 0 to exit : ");
                item = Input.GetInt();

                if (item > orderDao.getOrderByRoomNum(roomNum).getRoomServiceList().size())
                    System.out.println("Invalid choice! Enter again!");
                else if (item != 0)
                    break;
            } while (item != 0);

            int choice;
            Order o =  orderDao.getOrderByRoomNum(roomNum);
            do
            {
                System.out.println("1. Update status\n2. Update remarks\n3. Cancel order\n0. Exit\nInput : ");
                choice = Input.GetInt();

                switch (choice)
                {
                    case 1:
                        OrderStatus.displayOrderStatus();
                        System.out.print("Input : ");
                        int status = Input.GetInt();
                        orderDao.getOrderByRoomNum(roomNum).getStatusList().set(item-1, OrderStatus.returnStatus(status));
                        break;
                    case 2:
                        System.out.print("Enter new remark : ");
                        String remark = sc.nextLine();
                        orderDao.getOrderByRoomNum(roomNum).getRemarkList().set(item-1, remark);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice! Enter again!");
                }
            } while (choice != 0);
        }
    }

    public void removeOrder(int roomNum)
    {
        for (int i = 0; i < orderDao.getAllOrder().size(); i++)
        {
            if (orderDao.getAllOrder().get(i).getRoomNumber() == roomNum)
                orderDao.removeOrder(orderDao.getAllOrder().get(i));
        }
    }

    public void displayOrderbyRoomNum()
    {
        SimpleDateFormat formatDate = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        System.out.print("Enter room number : ");
        int roomNum = Input.GetInt();

        for (int i = 0; i < orderDao.getAllOrder().size(); i++)
        {
            if (orderDao.getAllOrder().get(i).getRoomNumber() == roomNum)
            {
                System.out.print("Room Number : " + orderDao.getAllOrder().get(i).getRoomNumber());

                if (orderDao.getAllOrder().get(i).getStatusList().size() != 0)
                {
                    for (int j = 0; j < orderDao.getAllOrder().get(i).getStatusList().size(); j++)
                    {
                        System.out.println(" Date & Time: " + formatDate.format(orderDao.getAllOrder().get(i).getDateList().get(j)) +
                                " Service Name : " + orderDao.getAllOrder().get(i).getRoomServiceList().get(j).getService() +
                                " Price : " + orderDao.getAllOrder().get(i).getRoomServiceList().get(j).getPrice() +
                                " Order Status : " + orderDao.getAllOrder().get(i).getStatusList().get(j) +
                                " Order Remark : " + orderDao.getAllOrder().get(i).getRemarkList().get(j));
                    }

                    break;
                }
                else
                {
                    System.out.println("\tOrder list is empty!");
                }
            }
        }
    }

    public void displayAllOrder()
    {
        SimpleDateFormat formatDate = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        for (int i = 0; i < orderDao.getAllOrder().size(); i++)
        {
            System.out.print("Room Number : " + orderDao.getAllOrder().get(i).getRoomNumber());

            if (orderDao.getAllOrder().get(i).getRoomServiceList().size() != 0)
            {
                for (int j = 0; j < orderDao.getAllOrder().get(i).getStatusList().size(); j++)
                {
                    System.out.print("\nDate & Time: " + formatDate.format(orderDao.getAllOrder().get(i).getDateList().get(j)) +
                            "\tService Name : " + orderDao.getAllOrder().get(i).getRoomServiceList().get(j).getService() +
                            "\tPrice : " + orderDao.getAllOrder().get(i).getRoomServiceList().get(j).getPrice() +
                            "\tOrder Status : " + orderDao.getAllOrder().get(i).getStatusList().get(j) +
                            "\tOrder Remark : " + orderDao.getAllOrder().get(i).getRemarkList().get(j));
                }
                System.out.println();
            }
            else
                System.out.println("\t\tOrder list is empty!");
        }
    }

    public void WritetoFile()
    {
        orderDao.updateFile();
    }

    public OrderDaoImpl getOrderDao()
    {
        return (OrderDaoImpl) orderDao;
    }
}