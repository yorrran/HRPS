import java.util.Scanner;

public class Input
{
    private static Scanner sc = new Scanner(System.in);

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
            }
            catch (Exception exc)
            {
                sc.nextLine();
                System.out.print("Enter only numbers!\nInput : ");
            }
        } while (invalid);

        return num;
    }

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
            }
            catch (Exception exc)
            {
                sc.nextLine();
                System.out.print("Enter only numbers!\nInput : ");
            }
        } while (invalid);

        return num;
    }

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
            }
            catch (Exception exc)
            {
                sc.nextLine();
                System.out.print("Enter only numbers!\nInput : ");
            }
        } while (invalid);

        return num;
    }

    public static String GetString()
    {
        String str = sc.next();
        return str;
    }
}