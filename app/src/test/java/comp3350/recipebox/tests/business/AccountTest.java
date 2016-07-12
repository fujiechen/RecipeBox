package comp3350.recipebox.tests.business;

import org.junit.Assert;
import org.junit.Test;

import comp3350.recipebox.business.Account;
import comp3350.recipebox.objects.User;

public class AccountTest
{

    @Test
    public void testCase()
    {
        Account account = Account.getAccount();

        Assert.assertFalse(account.isLogin());
        User user = new User(0,"me","password");
        User anotherUser = new User(1,"someone","another password");

        account.login(user);
        Assert.assertTrue(account.isLogin());
        Assert.assertEquals(account.getUser(),user);

        account.login(anotherUser);
        Assert.assertEquals(account.getUser(),user);

        account.logout();
        Assert.assertFalse(account.isLogin());

        account.login(anotherUser);
        Assert.assertNotEquals(account.getUser(),user);
    }
}
