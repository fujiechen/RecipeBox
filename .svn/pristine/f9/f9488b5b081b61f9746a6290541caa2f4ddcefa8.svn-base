package comp3350.recipebox.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import comp3350.recipebox.business.AccessRecipe;
import comp3350.recipebox.business.AccessReview;
import comp3350.recipebox.business.AccessUser;
import comp3350.recipebox.objects.Recipe;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.presentation.MainActivity;

public class ReviewTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public ReviewTest()
    {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
        AccessUser accessUser = new AccessUser();
        accessUser.addUser("new user", "1111");
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

        super.tearDown();
    }

    public void testAddReview()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("LOG IN");
        solo.waitForActivity("LoginActivity");
        solo.enterText(0, "new user");
        solo.enterText(1, "1111");
        solo.clickOnButton("LOG IN");

        solo.waitForActivity("MainActivity");
        solo.clickOnText("Home-Fried Cheese Sticks");
        solo.waitForActivity("ViewRecipeActivity");
        assertTrue(!solo.searchText("Add a Review", true));
        solo.goBack();

        solo.waitForActivity("MainActivity");
        solo.clickOnText("Roasted Pumpkin Seeds");
        solo.waitForActivity("ViewRecipeActivity");
        assertTrue(solo.searchText("Add Review", true));
        solo.clickOnText("Add Review");

        solo.waitForActivity("AddReviewActivity");
        assertTrue(solo.searchText("Enter Review"));
        assertTrue(solo.searchText("Submit"));
        assertTrue(!solo.getButton("Submit").isEnabled());
        solo.enterText(0, "TEST REVIEW");
        assertTrue(!solo.getButton("Submit").isEnabled());
        solo.setProgressBar(0, 1);
        assertTrue(solo.getButton("Submit").isEnabled());
        solo.clickOnButton("Submit");

        solo.waitForActivity("ViewRecipeActivity");
        solo.clickOnButton("View Reviews");
        solo.waitForActivity("ViewReviewListActivity");
        assertTrue(solo.searchText("TEST REVIEW"));
    }

    public void testDeleteReview()
    {
        Recipe recipe = new AccessRecipe().getRecipe(0);
        new AccessReview().addReview(recipe, 0, "Terrible", new AccessUser().getUserByName("new user"));

        solo.waitForActivity("MainActivity");
        solo.clickOnButton("LOG IN");

        solo.waitForActivity("LoginActivity");
        solo.enterText(0, "new user");
        solo.enterText(1, "1111");
        solo.clickOnButton("LOG IN");
        solo.clickOnButton("View Profile");

        solo.waitForActivity("ViewUserActivity");
        solo.clickOnText("Home-Fried Cheese Sticks");

        solo.waitForActivity("ViewReviewActivity");
        assertTrue(solo.searchText("Delete"));
        solo.clickOnButton("Delete");
        assertTrue(solo.searchText("Are you sure you want to delete this review?"));
        solo.clickOnButton("YES");

        solo.waitForActivity("MainActivity");
        solo.clickOnButton("View Profile");
        solo.waitForActivity("ViewUserActivity");
        assertTrue(!solo.searchText("Home-Fried Cheese Sticks", true));
    }
}

