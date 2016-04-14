import java.util.ArrayList;

public class GuestDaoImpl implements GuestDao
{
    ArrayList<Guest> guestList;
    private static final String fileName = "File/Guest.dat";

    public GuestDaoImpl()
    {
        guestList = DataIO.Read(fileName);
    }

    @Override
    public ArrayList<Guest> getAllGuest()
    {
        return this.guestList;
    }

    @Override
    public void addGuest(Guest newGuest)
    {
        this.guestList.add(newGuest);
    }

    @Override
    public void updateGuest(String oldId, Guest newGuest)
    {
        for (int i = 0; i < guestList.size(); i++)
        {
            if (guestList.get(i).getIdentity() == oldId)
                guestList.set(i, newGuest);
        }
    }

    @Override
    public void searchGuestbyId(String Identity)
    {
        boolean found = false;

        for (int i = 0; i < guestList.size(); i++)
        {
            if (Identity.equals(guestList.get(i).getIdentity()))
            {
                System.out.println("--------------------------------------------------" + "\n" +
                        "Identity : " + guestList.get(i).getIdentity() + "\n" +
                        "Name : " + guestList.get(i).getName() + "\n" +
                        "Country : " + guestList.get(i).getCountry() + "\n" +
                        "Gender : " + guestList.get(i).getGender() + "\n" +
                        "Address : " + guestList.get(i).getAddress() + "\n" +
                        "Nationality : " + guestList.get(i).getNationality() + "\n" +
                        "Phone Number : " + guestList.get(i).getPhoneNum() + "\n" +
                        "Email : " + guestList.get(i).getEmail() + "\n" +
                        "Credit Card Number : " + guestList.get(i).getCreditNum() + "\n" +
                        "--------------------------------------------------");
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Customer not found");
    }

    @Override
    public void searchGuestbyName(String name)
    {
        boolean found = false;

        for (int i = 0; i < guestList.size(); i++)
        {
            if ((name.toLowerCase()).equals(guestList.get(i).getName().toLowerCase()))
            {
                System.out.println("--------------------------------------------------" + "\n" +
                        "Identity : " + guestList.get(i).getIdentity() + "\n" +
                        "Name : " + guestList.get(i).getName() + "\n" +
                        "Country : " + guestList.get(i).getCountry() + "\n" +
                        "Gender : " + guestList.get(i).getGender() + "\n" +
                        "Address : " + guestList.get(i).getAddress() + "\n" +
                        "Nationality : " + guestList.get(i).getNationality() + "\n" +
                        "Phone Number : " + guestList.get(i).getPhoneNum() + "\n" +
                        "Email : " + guestList.get(i).getEmail() + "\n" +
                        "Credit Card Number : " + guestList.get(i).getCreditNum() + "\n" +
                        "--------------------------------------------------");

                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Customer not found");
    }

    public void updateFile()
    {
        DataIO.Write(fileName, guestList);
    }
}