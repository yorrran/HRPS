import java.io.Serializable;

public class Guest implements Serializable
{
    private String identity;
    private String name;
    private String country;
    private String gender;
    private String address;
    private String nationality;
    private int phoneNum;
    private String email;
    private int credit_no;

    public Guest(String identity, String name, String country, String gender, String address, String nationality, int phoneNum, String email, int credit_no)
    {
        this.identity = identity;
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.address = address;
        this.nationality = nationality;
        this.phoneNum = phoneNum;
        this.email = email;
        this.credit_no = credit_no;
    }

    public String getIdentity()
    {
        return identity;
    }

    public void setIdentity(String identity)
    {
        this.identity = identity;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public int getPhoneNum()
    {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getCredit_no()
    {
        return credit_no;
    }

    public void setCredit_no(int credit_no)
    {
        this.credit_no = credit_no;
    }
}