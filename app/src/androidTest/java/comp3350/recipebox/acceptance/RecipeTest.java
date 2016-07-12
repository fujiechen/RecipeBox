package comp3350.recipebox.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import comp3350.recipebox.business.AccessUser;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.presentation.MainActivity;

public class RecipeTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public RecipeTest()
    {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
        new AccessUser().addUser("new user", "1111");
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

    public void testAddRecipe()
    {
        solo.waitForActivity("MainActivity");
        assertTrue(!solo.searchText("Add a new recipe", true));
        solo.clickOnButton("LOG IN");
        solo.waitForActivity("LoginActivity");
        solo.enterText(0, "new user");
        solo.enterText(1, "1111");
        solo.clickOnButton("LOG IN");
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Add a new recipe");

        solo.waitForActivity("AddRecipeActivity");
        assertTrue(solo.searchText("Submit"));
        solo.clickOnButton("Submit");
        solo.assertCurrentActivity("Expected activity AddRecipeActivity", "AddRecipeActivity");
        solo.enterText(0, "TEST RECIPE: Ice");
        solo.enterText(1, "1 cup");
        solo.enterText(2, "Water");
        solo.clickOnButton("+");
        solo.enterText(1, "1 tsp");
        solo.enterText(2, "Sugar");
        solo.clickOnButton("+");
        solo.clickLongOnText("Sugar");

        solo.enterText(3, "Put the water in an ice tray. Leave in the freezer for at least 1 hour.");
        solo.clickOnButton("Submit");

        solo.waitForActivity("MainActivity");
        assertTrue(solo.searchText("TEST RECIPE: Ice"));
        solo.clickOnText("TEST RECIPE: Ice");

        solo.waitForActivity("ViewRecipeActivity");
        solo.assertCurrentActivity("Expected activity View RecipeActivity", "ViewRecipeActivity");
        assertTrue(solo.searchText("TEST RECIPE: Ice"));
        assertTrue(solo.searchText("Put the water in an ice tray. Leave in the freezer for at least 1 hour"));
        assertTrue(solo.searchText("Delete Recipe"));
        solo.clickOnText("Delete Recipe");
        assertTrue(solo.searchText("Are you sure you want to delete this recipe?"));
        assertTrue(solo.searchText("NO"));
        assertTrue(solo.searchText("YES"));
        solo.clickOnText("YES");
        solo.waitForActivity("MainActivity");
        assertTrue(!solo.searchText("TEST RECIPE: Ice", true));
    }

    public void testSearchRecipe()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnText("Search");
        assertTrue(solo.searchText("Enter Searching Keyword"));
        solo.enterText(0, "Eggs");
        solo.clickOnButton("Search");
        assertTrue(!solo.searchText("Baked Kale Chips", true));
        assertTrue(solo.searchText("Home-Fried Cheese Sticks", true));
        solo.clearEditText(0);
        solo.enterText(0, "Potato Chips");
        solo.clickOnButton("Search");
        assertTrue(!solo.searchText("Home-Fried Cheese Sticks", true));
    }

    public void testSearchNoResults()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnText("Search");
        solo.enterText(0, "long search string that won't match anything");
        solo.clickOnButton("Search");
    }

    public void testEditRecipe()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("LOG IN");

        solo.waitForActivity("LoginActivity");
        solo.enterText(0, "new user");
        solo.enterText(1, "1111");
        solo.clickOnButton("LOG IN");

        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Add a new recipe");

        solo.waitForActivity("AddRecipeActivity");
        solo.enterText(0, "test recipe");
        solo.enterText(1, "test");
        solo.enterText(2, "ingredient");
        solo.clickOnButton("+");
        solo.enterText(3, "test instructions");
        solo.clickOnButton("Submit");
        solo.clickOnButton("View Profile");
        solo.clickOnText("test recipe");
        assertTrue(solo.searchText("Edit Recipe"));
        solo.clickOnButton("Edit Recipe");

        solo.waitForActivity("EditRecipeActivity");
        System.out.println("EDITING");
        assertTrue(solo.searchText("test recipe"));
        assertTrue(solo.searchText("test ingredient"));
        assertTrue(solo.searchText("test instructions"));
        System.out.println("FOUND ALL THE STUFF");
        solo.clearEditText(0);
        solo.enterText(0, "new title");
        System.out.println("CHANGED TITLE");
        solo.clickOnButton("-");
        solo.enterText(1, "new");
        solo.enterText(2, "ingredient");
        solo.clickOnText("+");
        System.out.println("ENTERED NEW INGREDIENT");
        solo.clickOnButton("Submit");
        System.out.println("CLICKED THE BUTTON");

        solo.waitForActivity("ViewRecipeActivity");
        assertTrue(solo.searchText("new title"));
        assertTrue(solo.searchText("new user"));
        assertTrue(!solo.searchText("test ingredient"));
        assertTrue(solo.searchText("new ingredient"));
    }
}