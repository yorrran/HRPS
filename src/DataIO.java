import java.io.*;
import java.util.ArrayList;

public class DataIO
{
    @SuppressWarnings("rawtypes")
	public static ArrayList Read(String fileName)
    {
    	ArrayList objectList = new ArrayList();
        FileInputStream fis;
        ObjectInputStream in;

        try
        {
            fis = new FileInputStream(fileName);
            in = new ObjectInputStream(fis);
            objectList = (ArrayList) in.readObject();
            in.close();
            fis.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Data file not found");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return objectList;
    }

    public static void Write(String fileName, ArrayList oList)
    {
        FileOutputStream fos;
        ObjectOutputStream out;
        try
        {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(oList);
            out.close();
            fos.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}