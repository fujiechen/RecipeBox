package comp3350.recipebox.business;

import comp3350.recipebox.application.Main;
import comp3350.recipebox.application.Services;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.persistence.DataAccess;

public class AccessUser
{
	private DataAccess dataAccess;
	private static Account account = Account.getAccount();

	//Constructor
	public AccessUser()
	{
		dataAccess = Services.getDataAccess(Main.dbName);
	}

	//Constructor
	public AccessUser(DataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
		this.dataAccess = Services.getDataAccess(Main.dbName);
	}

	//Add a new user
	public boolean addUser(String name, String password)
	{
		if (name.trim().equals("") && password.equals(""))
			throw new Error("invalid name or password");

		if (dataAccess.getUserByName(name) == null) {
			dataAccess.addNewUser(name, password);
			return true;
		}
		return false;
	}

	//Get User Object by user name
	public User getUserByName(String name)
	{
		return dataAccess.getUserByName(name);
	}

	//Determine the given user name has already existed
	public boolean isFoundUser(String name)
	{
		return getUserByName(name) != null;
	}

	//Determine the given user id has already existed
	public boolean isFoundUser(int id)
	{
		return getUserByID(id) != null;
	}

	//Get User Object by user id
	public User getUserByID(int id)
	{
		return dataAccess.findUserByID(id);
	}

	//Determine the user logged in
	public boolean isLogin()
	{
		return account.isLogin();
	}

	//Try to log in
	public boolean login(String name, String password)
	{
		User user = dataAccess.getUserByName(name);
		if (user != null)
		{
			if (user.checkPassword(password))
			{
				account.login(user);
				return true;
			}
		}
		return false;
	}

	//Log out
	public boolean logout()
	{
		if (account.isLogin())
		{
			account.logout();
			return true;
		}
		return false;
	}

	//Delete all things about the given user id
	public boolean deleteUserProfile(int userID)
	{
		if (getUserByID(userID) != null)
		{
			dataAccess.deleteUserProfile(userID);
			return true;
		}
		return false;
	}

	//Get the current logged in user
	public User getCurrentAccount()
	{
		return account.getUser();
	}

	//Get the current logged in user id
	public int getCurrentUserID()
	{
		return account.getUserID();
	}
}
