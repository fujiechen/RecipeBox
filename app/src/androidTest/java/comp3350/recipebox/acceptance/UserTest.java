package comp3350.recipebox.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import comp3350.recipebox.business.AccessUser;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.presentation.MainActivity;

public class UserTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public UserTest()
    {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception
    {
        AccessUser accessUser = new AccessUser();

        accessUser.logout();
        User newUser;
        newUser = accessUser.getUserByName("new user");

        if (newUser != null)
            accessUser.deleteUserProfile(newUser.getID());

        solo.finishOpenedActivities();
    }

    public void testLogin()
    {

        solo.waitForActivity("MainActivity");
        Assert.assertEquals(new AccessUser().getCurrentAccount(), null);

        solo.clickOnButton("LOG IN");
        solo.assertCurrentActivity("Expected activity LoginActivity", "LoginActivity");
        Assert.assertTrue(solo.searchText("username"));
        Assert.assertTrue(solo.searchText("password"));

        solo.enterText(0, "new user");
        solo.enterText(1, "1111");
        solo.clickOnButton("LOG IN");
        Assert.assertTrue(solo.searchText("Unrecognised user new user. Create new account?"));
        solo.clickOnButton("NO");

        solo.clickOnButton("SIGN UP");
        solo.assertCurrentActivity("Expected activity MainActivity", "MainActivity");
        assertTrue(solo.searchText("LOG OUT"));
        solo.clickOnButton("LOG OUT");
        assertTrue(solo.searchText("LOG IN"));

        solo.clickOnButton("LOG IN");
        solo.enterText(0, "new user");
        solo.enterText(1, "1111");
        solo.clickOnButton("SIGN UP");
        Assert.assertTrue(solo.searchText("User new user already exists. Attempt log in?"));
        solo.clickOnButton("NO");
        solo.assertCurrentActivity("Expected activity LoginActvity", "LoginActivity");
        solo.clickOnButton("LOG IN");
        solo.assertCurrentActivity("Expected activity MainActivity", "MainActivity");
        solo.clickOnButton("LOG OUT");
    }

    public void testProfile()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("LOG IN");

        solo.waitForActivity("LoginActivity");
        solo.enterText(0, "new user");
        solo.enterText(1, "1111");
        solo.clickOnButton("SIGN UP");

        solo.waitForActivity("MainActivity");
        solo.clickOnButton("View Profile");

        solo.waitForActivity("VewUserActivity");
        assertTrue(solo.searchText("User: new user"));
        assertTrue(solo.searchText("Posted Recipes"));
        assertTrue(solo.searchText("Posted Reviews"));
        assertTrue(solo.searchText("Delete Profile"));
        solo.clickOnButton("Delete Profile");
        assertTrue(solo.searchText("Are you sure you want to delete your account, including all posted recipes and reviews?"));
        solo.clickOnButton("YES");

        solo.waitForActivity("MainActivity");
        solo.assertCurrentActivity("Expected activity MainActivity", "MainActivity");
    }

    public void testBadPassword()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("LOG IN");
        solo.enterText(0, "TEST");
        solo.enterText(1, "wrong password");
        solo.clickOnButton("LOG IN");
        assertTrue(solo.searchText("Password invalid."));
        solo.goBack();
        solo.clickOnButton("SIGN UP");
        solo.clickOnText("YES");
        assertTrue(solo.searchText("Password invalid."));
        solo.goBack();
        solo.goBack();
    }
}