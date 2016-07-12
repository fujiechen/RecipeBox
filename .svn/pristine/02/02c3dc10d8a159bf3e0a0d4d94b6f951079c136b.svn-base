package comp3350.recipebox.business;


import comp3350.recipebox.objects.User;

public class Account
{
	private static Account ourInstance = new Account();
	private User user;

	public static Account getAccount()
	{
		return ourInstance;
	}

	private Account()
	{
		user = null;
	}

	public boolean isLogin()
	{
		return user != null;
	}

	public void login(User user)
	{
		if(!isLogin())
		{
			this.user = user;
		}
	}

	public void logout()
	{
		if(isLogin())
		{
			user = null;
		}
	}

	public User getUser()
	{
		return user;
	}

	public int getUserID()
	{
		return user.getID();
	}
}
