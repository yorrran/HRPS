/**
 * Class that specifies the status of an room service
 */
public enum OrderStatus
{
    Delivered, Delivering, Preparing, OrderReceived;

    /**
     * Display all the order room service
     */
    public static void displayOrderStatus()
    {
        int i = 1;
        for (OrderStatus status : OrderStatus.values())
        {
            System.out.println(i + ". " + status);
            i++;
        }
    }

    /**
     * Returns the room service status based on user input.
     *
     * @param statusNumber the input value by user
     * @return the order status
     */
    public static OrderStatus returnStatus(int statusNumber)
    {
        switch (statusNumber)
        {
            case 1:
                return Delivered;
            case 2:
                return Delivering;
            case 3:
                return Preparing;
            case 4:
                return OrderReceived;
            default:
                return null;
        }
    }
}