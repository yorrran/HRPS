import java.io.*;
import java.util.ArrayList;

/**
 * Read and write objects to data files.
 */
public class DataIO {
	/**
	 * Returns an ArrayList of the object data read from the data file.
	 * 
	 * @param fileName
	 *            An absolute path giving the location of the data file.
	 * @return the array list object loaded from data file.
	 */
	public static ArrayList Read(String fileName) {
		ArrayList objectList = new ArrayList();
		FileInputStream fis;
		ObjectInputStream in;

		try {
			fis = new FileInputStream(fileName);
			in = new ObjectInputStream(fis);
			objectList = (ArrayList) in.readObject();
			in.close();
			fis.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Data file not found");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return objectList;
	}

	/**
	 * Writes an ArrayList of object data read into the data file.
	 * 
	 * @param fileName
	 *            An absolute path giving the location of the data file.
	 * @param oList
	 *            An ArrayList of object to be written into the data file.
	 */
	public static void Write(String fileName, ArrayList oList) {
		FileOutputStream fos;
		ObjectOutputStream out;
		try {
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(oList);
			out.close();
			fos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}