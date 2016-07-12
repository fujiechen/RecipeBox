package comp3350.recipebox.objects;

public class User
{
    private int id;
    private String name;
    private String password;

    public User(int id)
    {
        this.id = id;
    }

    public User(int id, String name, String password)
    {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getID()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return "UserID: " + id + " User Name: "
                + name + " User Password: " + password;
    }

    public boolean equals(Object object)
    {
        boolean result;
        User user;

        result = false;
        if (object instanceof User)
        {
            user = (User) object;
            if (user.id == id)
            {
                result = true;
            }
        }

        return result;
    }

    public boolean checkPassword(Object object)
    {
        boolean result = false;
        User user;
        if (object instanceof User)
        {
            user = (User) object;
            if (equals(user) && user.password.equals(password))
            {
                result = true;
            }
        }
        return result;
    }

    public boolean checkPassword(String password)
    {
        return password.equals(this.password);
    }
}