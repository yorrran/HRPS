public enum OrderStatus
{
    Delivered, Delivering, Preparing, OrderReceived;

    /**
     *
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
     *
     * @param statusNumber
     * @return
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