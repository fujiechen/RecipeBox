package comp3350.recipebox.business;

import comp3350.recipebox.application.Main;
import comp3350.recipebox.application.Services;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.persistence.DataAccess;

public class AccessUser
{
	private DataAccess dataAccess;
	private static Account account = Account.getAccount();

	public AccessUser()
	{
		dataAccess = Services.getDataAccess(Main.dbName);
	}

	public AccessUser(DataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
		this.dataAccess = Services.getDataAccess(Main.dbName);
	}

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

	public User getUserByName(String name)
	{
		return dataAccess.getUserByName(name);
	}

	public boolean isFoundUser(String name)
	{
		return getUserByName(name) != null;
	}

	public boolean isFoundUser(int id)
	{
		return getUserByID(id) != null;
	}

	public User getUserByID(int id)
	{
		return dataAccess.findUserByID(id);
	}

	public boolean isLogin()
	{
		return account.isLogin();
	}

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

	public boolean logout()
	{
		if (account.isLogin())
		{
			account.logout();
			return true;
		}
		return false;
	}

	public boolean deleteUserProfile(int userID)
	{
		if (getUserByID(userID) != null)
		{
			dataAccess.deleteUserProfile(userID);
			return true;
		}
		return false;
	}

	public User getCurrentAccount()
	{
		return account.getUser();
	}

	public int getCurrentUserID()
	{
		return account.getUserID();
	}
}
