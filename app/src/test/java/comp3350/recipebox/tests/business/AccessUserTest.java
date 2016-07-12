package comp3350.recipebox.tests.business;

import org.junit.Test;

import comp3350.recipebox.application.Services;
import comp3350.recipebox.business.AccessUser;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.tests.persistence.DataAccessStub;

import junit.framework.Assert;

public class AccessUserTest
{
	public AccessUser accessUser;

	public AccessUserTest()
	{
		String testing = "testing";
		DataAccessStub dataAccess = new DataAccessStub(testing);
		Services.createDataAccess(dataAccess);
		accessUser = new AccessUser();
	}

	@Test
	public void testAccessUser()
	{
		String userName = "MrA";
		String userName1 = "223344";
		String userName2 = "pETeR";
		String userName3 = "manitoba Winnipeg";

		String userPw = "a01234";
		String userPw1 = "a01234";
		String userPw2 = "0a1b2c";
		String userPw3 = "asdfg";

		accessUser.addUser(userName, userPw);
		accessUser.addUser(userName1, userPw1);
		accessUser.addUser(userName2, userPw2);
		accessUser.addUser(userName3, userPw3);

		User user = accessUser.getUserByName(userName);
		User user1 = accessUser.getUserByName(userName1);
		User user2 = accessUser.getUserByName(userName2);
		User user3 = accessUser.getUserByName(userName3);

		Assert.assertEquals(user1.getName(), userName1);
		Assert.assertEquals(user2.getPassword(), userPw2);
		Assert.assertEquals(user3.getPassword(), userPw3);

		Assert.assertEquals(accessUser.getUserByID(user.getID()), user);
		Assert.assertEquals(accessUser.getUserByID(user1.getID()), user1);

		Assert.assertTrue(accessUser.isFoundUser(3));
		Assert.assertFalse(accessUser.isFoundUser(Integer.MAX_VALUE));
		Assert.assertTrue(accessUser.isFoundUser(userName3));
		Assert.assertFalse(accessUser.isFoundUser("DontExist"));

		accessUser.deleteUserProfile(user.getID());
		Assert.assertFalse(accessUser.isFoundUser(userName));
		Assert.assertFalse(accessUser.isFoundUser(user.getID()));
	}

	@Test
	public void testInVailData()
	{
		String userName = "     ";
		String userName1 = "";
		String userPw = "";
		String userPw1 = "123";
		try {
			accessUser.addUser(userName, userPw);
			Assert.fail();
		} catch (Error e) {
		}
		try {
			accessUser.addUser(userName1, userPw1);
			Assert.fail();
		} catch (Error e) {
		}
	}

	@Test
	public void testLogging()
	{
		String userName = "UniversityCafe";
		String userPw = "a12345";

		accessUser.addUser(userName, userPw);
		int userID = accessUser.getUserByName(userName).getID();

		Assert.assertFalse(accessUser.login("error", "xxxxx"));
		Assert.assertTrue(accessUser.login(userName, userPw));

		Assert.assertTrue(accessUser.isLogin());

		Assert.assertFalse(accessUser.login("errorr", userPw));

		Assert.assertEquals(accessUser.getCurrentAccount().getName(), userName);
		Assert.assertEquals(accessUser.getCurrentAccount().getPassword(), userPw);

		accessUser.logout();

		Assert.assertFalse(accessUser.isLogin());
		Assert.assertNull(accessUser.getCurrentAccount());
	}
}
