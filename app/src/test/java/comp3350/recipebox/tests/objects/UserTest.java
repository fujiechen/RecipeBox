package comp3350.recipebox.tests.objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import comp3350.recipebox.objects.User;

public class UserTest {

    @Test
    public void testIngredient1()
    {
        String userName;
        int userID;
        String userPassword;

        userName = "User123";
        userID = 1;
        userPassword = "password123";

        User user1;
        User user2;

        user1 = new User(userID, userName, userPassword);
        Assert.assertNotNull(user1);
        Assert.assertTrue(userName.equals(user1.getName()));
        Assert.assertTrue(userID == user1.getID());
        Assert.assertTrue(userPassword == user1.getPassword());

        userName = "NewUser321";

        user1.setName(userName);
        Assert.assertTrue(userName.equals(user1.getName()));

        user2 = new User(userID, userName, userPassword);
        Assert.assertTrue(user1.equals(user2));
        Assert.assertTrue(user1.checkPassword(user2));
        Assert.assertTrue(user1.toString().equals(user2.toString()));
    }

}
