import java.util.ArrayList;

public class GuestDaoImpl implements GuestDao
{
    ArrayList<Guest> guestList;
    private static final String fileName = "File/Guest.dat";

    public GuestDaoImpl()
    {
        //guestList = new ArrayList<>();
        //guestList.add(new Guest("00001", "Mary","SG", "F", "NTU,", "Singaporean", 999,"xxx@gmail.com", 124));
        //guestList.add(new Guest("00002", "John","MY", "M", "NUS,", "Malaysian", 111,"ggg@gmail.com", 346));
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
        for (int i = 0; i < guestList.size(); i++)
        {
            if (Identity.equals(guestList.get(i).getIdentity()))
            {
                System.out.println("Identity : " + guestList.get(i).getIdentity() + "\t" +
                        "Name : " + guestList.get(i).getName() + "\t" +
                        "Country : " + guestList.get(i).getCountry() + "\t" +
                        "Gender : " + guestList.get(i).getGender() + "\t" +
                        "Address : " + guestList.get(i).getAddress() + "\t" +
                        "Nationality : " + guestList.get(i).getNationality() + "\t" +
                        "Phone Number : " + guestList.get(i).getPhoneNum() + "\t" +
                        "Email : " + guestList.get(i).getEmail() + "\t" +
                        "Credit Card Number : " + guestList.get(i).getCredit_no());
            }
        }

        System.out.println("Customer is not found");
    }

    @Override
    public void searchGuestbyName(String name)
    {
        for (int i = 0; i < guestList.size(); i++)
        {
            if ((name.toLowerCase()).equals(guestList.get(i).getName().toLowerCase()))
            {
                System.out.println("Identity : " + guestList.get(i).getIdentity() + "\t" +
                        "Name : " + guestList.get(i).getName() + "\t" +
                        "Country : " + guestList.get(i).getCountry() + "\t" +
                        "Gender : " + guestList.get(i).getGender() + "\t" +
                        "Address : " + guestList.get(i).getAddress() + "\t" +
                        "Nationality : " + guestList.get(i).getNationality() + "\t" +
                        "Phone Number : " + guestList.get(i).getPhoneNum() + "\t" +
                        "Email : " + guestList.get(i).getEmail() + "\t" +
                        "Credit Card Number : " + guestList.get(i).getCredit_no());
            }
        }
    }

    public void updateFile()
    {
        DataIO.Write(fileName, guestList);
    }
}