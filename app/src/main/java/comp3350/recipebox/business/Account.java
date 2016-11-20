package comp3350.recipebox.business;


import comp3350.recipebox.objects.User;

//Singleton
public class Account
{
	//We can only support one instance of this class
	private static Account ourInstance = new Account();
	//Which means we can only support one user login
	private User user;

	//Get the current instance
	public static Account getAccount()
	{
		return ourInstance;
	}

	//Constructor
	private Account()
	{
		user = null;
	}

	//Determine if there is any user log in
	public boolean isLogin()
	{
		return user != null;
	}

	//The given user logs in
	public void login(User user)
	{
		if(!isLogin())
		{
			this.user = user;
		}
	}

	//The current user logs out
	public void logout()
	{
		if(isLogin())
		{
			user = null;
		}
	}

	//Get the user who logged in
	public User getUser()
	{
		return user;
	}

	//Get the logged in user's ID
	public int getUserID()
	{
		return user.getID();
	}
}
