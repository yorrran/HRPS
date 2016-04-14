import java.util.Scanner;

/**
 * A special class to read input from console with error handling.
 */
public class Input
{
    private static Scanner sc = new Scanner(System.in);

    /**
     * Gets the integer value from input.
     *
     * @return the input value of data type int
     */
    public static int GetInt()
    {
        int num = 0;
        boolean invalid = true;

        do
        {
            try
            {
                num = sc.nextInt();
                invalid = false;
            } catch (Exception exc)
            {
                sc.nextLine();
                System.out.print("Enter only numbers!\nInput : ");
            }
        } while (invalid);

        return num;
    }

    /**
     * Gets the double value from input.
     *
     * @return the input value of data type double
     */
    public static double GetDouble()
    {
        double num = 0;
        boolean invalid = true;

        do
        {
            try
            {
                num = sc.nextDouble();
                invalid = false;
            } catch (Exception exc)
            {
                sc.nextLine();
                System.out.print("Enter only numbers!\nInput : ");
            }
        } while (invalid);

        return num;
    }

    /**
     * Gets the long value from input.
     *
     * @return the input value of data type long
     */
    public static long GetLong()
    {
        long num = 0;
        boolean invalid = true;

        do
        {
            try
            {
                num = sc.nextLong();
                invalid = false;
            } catch (Exception exc)
            {
                sc.nextLine();
                System.out.print("Enter only numbers!\nInput : ");
            }
        } while (invalid);

        return num;
    }

    /**
     * Gets the String value from input.
     *
     * @return the input value of data type String
     */
    public static String GetString()
    {
        String str = sc.next();
        return str;
    }
}