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
            }//test
            catch (Exception exc)
            {
                sc.nextLine();
                System.out.print("Enter only integer number!\nInput : ");
            }
        } while (invalid);

        return num;
    }

    public static double GetDouble()
    {
        double num = 0.5;
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
                System.out.print("Enter only double or integer!\nInput : ");
            }
        } while (invalid);

        return num;
    }

    public static String GetString()
    {
        String str = " ";
        str = sc.next();
        return str;//TESTETESTETETsss
    }
}